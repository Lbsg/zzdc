package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liubaoshuai_i on 2018/4/14.
 */
@Service
public class RecommendService {

    /**
     * 根据用户名构建item_profile,user_profile
     */


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
     * @param user_profile
     * @param item_profile
     * @return
     */
    public List<String> baseCBRecommend(double[] user_profile, Map<String, double[]> item_profile) {
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
        return recommendName;
    }

}
