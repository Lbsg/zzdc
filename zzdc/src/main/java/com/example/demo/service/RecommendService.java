package com.example.demo.service;

import com.example.demo.dao.ShopDishesEntityMapper;
import com.example.demo.dao.UserHistoryorderEntityMapper;
import com.example.demo.entity.ShopDishesEntity;
import com.example.demo.entity.ShopDishesEntityExample;
import com.example.demo.entity.UserHistoryorderEntity;
import com.example.demo.entity.UserHistoryorderEntityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liubaoshuai_i on 2018/4/14.
 * 构建一维数据，关键是高维不会
 */
@Service
public class RecommendService {

    @Autowired
    private UserHistoryorderEntityMapper userHistoryMapper;

    @Autowired
    private ShopDishesEntityMapper shopDishesMapper;

    String[] ingredients = {"a", "b", "c", "d", "e", "f"};

    /**
     * 根据用户名构建user_profile
     * @param userName
     * @return
     */
    private double[] getUserProfile(String userName) {
        UserHistoryorderEntityExample userHistoryExample = new UserHistoryorderEntityExample();
        UserHistoryorderEntityExample.Criteria criteria = userHistoryExample.createCriteria();

        criteria.andUsernameEqualTo(userName);
        double[] user_profile = {0, 0, 0, 0, 0, 0};
        int[] count = {0, 0, 0, 0, 0, 0};
        double avgCount = 0;
        int orderCount = 0;
        ShopDishesEntity shopDishes;
        List<UserHistoryorderEntity> userList = userHistoryMapper.selectByExample(userHistoryExample);
        for (UserHistoryorderEntity item : userList) {
            orderCount += item.getCount();
        }
        avgCount = (double)orderCount / (double)userList.size();
        for (UserHistoryorderEntity item : userList) {
            ShopDishesEntityExample shopDishesExample = new ShopDishesEntityExample();
            ShopDishesEntityExample.Criteria shopDishesCriteria = shopDishesExample.createCriteria();
            shopDishesCriteria.andShopnameEqualTo(item.getShopname());
            shopDishesCriteria.andShopdishesEqualTo(item.getShopdishes());
            List<ShopDishesEntity> shopDishList = shopDishesMapper.selectByExample(shopDishesExample);
            shopDishes = shopDishList.get(0);
            for (int i = 0; i < ingredients.length; i++) {
                if (shopDishes.getIngredient().contains(ingredients[i])) {
                    count[i] += 5;
                    user_profile[i] = user_profile[i] + item.getCount() - avgCount;
                }
            }
        }
        for (int i = 0; i < user_profile.length; i++) {
            if (user_profile[i] == 0) {
                user_profile[i] = 0;
            }
            else {
                user_profile[i] = user_profile[i] / count[i];
            }

        }
        return user_profile;
    }

    /**
     * 构建菜品item_profile
     * @return
     */
    private Map<String, double[]> getItemProfile() {
        Map<String, double[]> item_profile = new HashMap<>();
        ShopDishesEntityExample shopDishesExample = new ShopDishesEntityExample();
        List<ShopDishesEntity> shopDishesList = shopDishesMapper.selectByExample(shopDishesExample);
        for (ShopDishesEntity shopDishesEntity : shopDishesList) {
            double[] contains = {0, 0, 0, 0, 0, 0};
            for (int i = 0; i < ingredients.length; i++) {
                if (shopDishesEntity.getIngredient().contains(ingredients[i])) {
                    contains[i] = 1;
                }
            }
            item_profile.put(shopDishesEntity.getShopdishes(), contains);
        }
        return item_profile;
    }

    /**
     * 计算两个向量之间的cos值(推荐菜品)
     * @param item_profile
     * @param user_profile
     * @return
     */
    private double countCos(double[] user_profile, double[] item_profile) {
        double cos, sum_user = 0, sum_item = 0, sum_all = 0;
        for (int i = 0; i< item_profile.length; i++) {
            sum_all += user_profile[i] * item_profile[i];
            sum_user += Math.pow(user_profile[i], 2);
            sum_item += Math.pow(item_profile[i], 2);
        }
        cos = sum_all / (Math.sqrt(sum_item) + Math.sqrt(sum_user));
        return cos;
    }

    /**
     * 根据特定用户喜好向量和所有物品向量计算推荐物品(推荐菜品)
     * @param userName
     * @return
     */
    public List<String> baseCBRecommend(String userName) {
        double[] user_profile = getUserProfile(userName);
        Map<String, double[]> item_profile = getItemProfile();
        List<String> recommendName = new ArrayList<>();
        Map<String, Double> everyCos = new HashMap<>();
        for (String key : item_profile.keySet()) {
                everyCos.put(key, countCos(user_profile, item_profile.get(key)));
        }
        List<Map.Entry<String, Double>> cosList = new ArrayList<Map.Entry<String, Double>>(everyCos.entrySet());
        Collections.sort(cosList, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (Map.Entry<String, Double> map : cosList) {
            recommendName.add(map.getKey());
        }
        if (recommendName.size() > 1) {
            return recommendName.subList(0, 2);
        }else return recommendName;
    }

}
