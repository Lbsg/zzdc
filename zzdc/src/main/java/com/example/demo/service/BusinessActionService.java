package com.example.demo.service;

import com.example.demo.dao.BusinessEntityMapper;
import com.example.demo.dao.ShopDishesEntityMapper;
import com.example.demo.dao.ShopEntityMapper;
import com.example.demo.dao.ShopHistoryorderEntityMapper;
import com.example.demo.entity.*;
import com.example.demo.utils.CommonException;
import com.example.demo.utils.HSSFUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liubaoshuai_i on 2018/4/12.
 */
@Service
public class BusinessActionService {

    @Autowired
    private BusinessEntityMapper businessMapper;

    @Autowired
    private ShopEntityMapper shopMapper;

    @Autowired
    private ShopDishesEntityMapper shopDishesMapper;

    @Autowired
    private ShopHistoryorderEntityMapper shopHistoryMapper;


    /**
     * 处理时间
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public Map<String, Date> convertTime(String startTime, String endTime) throws ParseException {
        Map<String, Date> timeMap = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date start = dateFormat.parse(startTime);
        Date end = dateFormat.parse(endTime);
        timeMap.put("s_time", start);
        timeMap.put("e_time", end);
        return timeMap;
    }

    /**
     * 商家用户登录
     * @param name
     * @param password
     * @return
     * @throws CommonException
     */
    public boolean login(String name, String password) throws CommonException {
        boolean isPass = false;
        BusinessEntityExample businessExample = new BusinessEntityExample();
        BusinessEntityExample.Criteria criteria = businessExample.createCriteria();
        criteria.andNameEqualTo(name);
        int count = businessMapper.countByExample(businessExample);
        if (count == 0) {
            throw new CommonException("用户名不存在");
        }else {
            criteria.andPasswordEqualTo(password);
            count = businessMapper.countByExample(businessExample);
            if (count == 0) {
                throw new CommonException("密码错误");
            }else {
                isPass = true;
            }
        }
        return isPass;
    }

    /**
     * 商家用户创建店铺
     * @param shopName
     * @param hostName
     * @param shopTopic
     * @param address
     * @param start
     * @param end
     * @return
     * @throws CommonException
     */
    public int createShop(String shopName, String hostName, String shopTopic, String address, String start, String end) throws CommonException, ParseException {
        ShopEntity shop = new ShopEntity();
        int createCount;
        ShopEntityExample shopExample = new ShopEntityExample();
        ShopEntityExample.Criteria criteria = shopExample.createCriteria();
        criteria.andShopnameEqualTo(shopName);
        int selectCount = shopMapper.countByExample(shopExample);
        if (selectCount == 0) {
            if (shopName != null) {
                shop.setShopname(shopName);
            }
            if (hostName != null) {
                shop.setHostname(hostName);
            }
            if (shopTopic != null) {
                shop.setShoptopic(shopTopic);
            }
            if (address != null) {
                shop.setAddress(address);
            }
            if (start != null && end != null) {
                Map<String, Date> time = convertTime(start, end);
                shop.setStarttime(time.get("s_time"));
                shop.setEndtime(time.get("e_time"));
            }
            createCount = shopMapper.insertSelective(shop);
        }else {
            throw new CommonException("该店铺已经存在!");
        }
        return createCount;
    }

    /**
     * 商家修改指定店铺信息
     * @param shopName
     * @param shopTopic
     * @param address
     * @param start
     * @param end
     * @return
     */
    public int editShop(String shopName, String shopTopic, String address, String start, String end) throws ParseException {
        int editCount;
        ShopEntityExample shopExample = new ShopEntityExample();
        ShopEntityExample.Criteria criteria = shopExample.createCriteria();
        criteria.andShopnameEqualTo(shopName);
        ShopEntity shop = new ShopEntity();
        shop.setShopname(shopName);
        if (shopTopic != null) {
            shop.setShoptopic(shopTopic);
        }
        if (address != null) {
            shop.setAddress(address);
        }
        if (start != null && end != null) {
            Map<String, Date> time = convertTime(start, end);
            shop.setStarttime(time.get("s_time"));
            shop.setEndtime(time.get("e_time"));
        }
         editCount = shopMapper.updateByExampleSelective(shop, shopExample);
        return editCount;
    }

    /**
     * 商家删除指定店铺
     * @return
     * @param shopName
     * @return
     */
    public int delShop(String shopName){
        int delCount;
        ShopEntityExample shopExample = new ShopEntityExample();
        ShopEntityExample.Criteria criteria = shopExample.createCriteria();
        criteria.andShopnameEqualTo(shopName);
        delCount = shopMapper.deleteByExample(shopExample);
        return delCount;
    }

    /**
     * 商家查询指定店铺信息
     * @param shopName
     * @return
     */
    public List<ShopEntity> search(String shopName){
        List<ShopEntity> shopList;
        ShopEntityExample shopExample = new ShopEntityExample();
        ShopEntityExample.Criteria criteria = shopExample.createCriteria();
        criteria.andShopnameEqualTo(shopName);
        shopList = shopMapper.selectByExample(shopExample);
        return shopList;
    }

    /**
     * 商家增加指定店铺商品
     * @param shopName
     * @param shopDishesName
     * @param price
     * @param style
     * @return
     * @throws CommonException
     */
    public int addShopDishes(String shopName, String shopDishesName, String price, String style, String ingredient) throws CommonException {
        int addCount;
        ShopDishesEntityExample shopDishesExample = new ShopDishesEntityExample();
        ShopDishesEntityExample.Criteria criteria = shopDishesExample.createCriteria();
        criteria.andShopnameEqualTo(shopName);
        criteria.andShopdishesEqualTo(shopDishesName);
        int count = shopDishesMapper.countByExample(shopDishesExample);
        if (count > 0) {
            throw new CommonException("该店铺已存在该商品!");
        }else {
            ShopDishesEntity shopDishes = new ShopDishesEntity();
            shopDishes.setShopname(shopName);
            shopDishes.setShopdishes(shopDishesName);
            if (price != null) {
                shopDishes.setPrice(price);
            }
            if (style != null) {
                shopDishes.setStyle(style);
            }
            if (ingredient != null) {
                shopDishes.setIngredient(ingredient);
            }
            addCount = shopDishesMapper.insertSelective(shopDishes);
        }
        return addCount;
    }

    /**
     * 商家修改指定店铺商品
     * @param shopName
     * @param shopDishesName
     * @param price
     * @param style
     * @return
     */
    public int editShopDishes(String shopName, String shopDishesName, String price, String style, String ingredient){
        int editCount;
        ShopDishesEntityExample shopDishesExample = new ShopDishesEntityExample();
        ShopDishesEntityExample.Criteria criteria = shopDishesExample.createCriteria();
        ShopDishesEntity shopDishesEntity = new ShopDishesEntity();
        criteria.andShopnameEqualTo(shopName);
        criteria.andShopdishesEqualTo(shopDishesName);
        if (price != null) {
            shopDishesEntity.setPrice(price);
        }
        if (style != null) {
            shopDishesEntity.setStyle(style);
        }
        if (ingredient != null) {
            shopDishesEntity.setIngredient(ingredient);
        }
        editCount = shopDishesMapper.updateByExampleSelective(shopDishesEntity, shopDishesExample);
        return editCount;
    }

    /**
     * 商家删除指定店铺指定商品
     * @param shopName
     * @param shopDishesName
     * @return
     */
    public int delShopDishes(String shopName, String shopDishesName) {
        int delCount;
        ShopDishesEntityExample shopDishesExample = new ShopDishesEntityExample();
        ShopDishesEntityExample.Criteria criteria = shopDishesExample.createCriteria();
        criteria.andShopdishesEqualTo(shopDishesName);
        criteria.andShopnameEqualTo(shopName);
        delCount = shopDishesMapper.deleteByExample(shopDishesExample);
        return delCount;
    }

    /**
     * 商家查询指定店铺所有商品
     * @param shopName
     * @return
     */
    public List<ShopDishesEntity> searchShopDishes(String shopName) {
        List<ShopDishesEntity> shopDishesList;
        ShopDishesEntityExample shopDishesExample = new ShopDishesEntityExample();
        ShopDishesEntityExample.Criteria criteria = shopDishesExample.createCriteria();
        criteria.andShopnameEqualTo(shopName);
        shopDishesList = shopDishesMapper.selectByExample(shopDishesExample);
        return shopDishesList;
    }

    /**
     * 商家查询当月指定店铺所有订单情况
     * @param shopName
     * @return
     */
    public List<ShopHistoryorderEntity> searchHistoryOrders(String shopName) {
        List<ShopHistoryorderEntity> shopHistoryList;
        ShopHistoryorderEntityExample shopHistoryExample = new ShopHistoryorderEntityExample();
        ShopHistoryorderEntityExample.Criteria criteria = shopHistoryExample.createCriteria();
        criteria.andShopnameEqualTo(shopName);
        shopHistoryList = shopHistoryMapper.selectByExample(shopHistoryExample);
        return shopHistoryList;
    }

    /**
     * 商家获取当月指定店铺月报表
     * @param shopName
     * @param title
     * @return
     */
    public HSSFWorkbook getExcel(String shopName, String[] title, String month) throws Exception {
        HSSFWorkbook workbook;
        List<ShopHistoryorderEntity> shopHistory = searchHistoryOrders(shopName);
        workbook = HSSFUtils.getExcel(shopHistory, title, shopName, month);
        return workbook;
    }

    /**
     * 商家查询所有店铺所有商品
     * @param name
     * @return
     */
    public Map<String, List<ShopHistoryorderEntity>> getAll(String name) {
        Map<String, List<ShopHistoryorderEntity>> resultMap = new HashMap<>();
        ShopEntityExample shopExample = new ShopEntityExample();
        ShopEntityExample.Criteria criteria = shopExample.createCriteria();
        criteria.andHostnameEqualTo(name);
        List<ShopEntity> getAllShop = shopMapper.selectByExample(shopExample);
        for (ShopEntity item : getAllShop) {
            List<ShopHistoryorderEntity> historyOrder = searchHistoryOrders(item.getShopname());
            resultMap.put(item.getShopname(), historyOrder);
        }
        return resultMap;
    }

    /**
     * 展示信誉值较高的店铺以及商铺对应菜品
     * @return
     */
    public Map<String, List<ShopDishesEntity>> showHighCreditShop() {
        Map<String, List<ShopDishesEntity>> map = new HashMap<>();
        List<ShopEntity> shopList = shopMapper.selectByExample(null);
        for (ShopEntity sh: shopList) {
            if (sh.getCredit() > 50) {
                ShopDishesEntityExample dishes = new ShopDishesEntityExample();
                ShopDishesEntityExample.Criteria shCriteria = dishes.createCriteria();
                shCriteria.andShopnameEqualTo(sh.getShopname());
                List<ShopDishesEntity> dishList = shopDishesMapper.selectByExample(dishes);
                map.put(sh.getShopname(), dishList);
            }
        }
        return map;
    }
}

