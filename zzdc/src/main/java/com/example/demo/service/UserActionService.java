package com.example.demo.service;

import com.example.demo.dao.ShopHistoryorderEntityMapper;
import com.example.demo.dao.UserEntityMapper;
import com.example.demo.dao.UserHistoryorderEntityMapper;
import com.example.demo.entity.*;
import com.example.demo.utils.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liubaoshuai_i on 2018/4/11.
 */
@Service
public class UserActionService {

    @Autowired
    private UserEntityMapper userMapper;

    @Autowired
    private UserHistoryorderEntityMapper userHistoryMapper;

    @Autowired
    private ShopHistoryorderEntityMapper shopHistoryMapper;

    /**
     * 个人用户登录
     * @param name
     * @param password
     * @return
     * @throws CommonException
     */
    public boolean login(String name, String password) throws CommonException {
        boolean isPass = false;
        UserEntityExample userExample = new UserEntityExample();
        UserEntityExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(name);
        int userCount = userMapper.countByExample(userExample);
        if (userCount == 0) {
            throw new CommonException("用户名不存在!");
        }else {
            criteria.andPasswordEqualTo(password);
            userCount = userMapper.countByExample(userExample);
            if (userCount == 0) {
                throw new CommonException("密码有误!");
            }else {
                isPass = true;
            }
        }
        return isPass;
    }

    /**
     * 个人用户下单
     * @param userName
     * @param shopName
     * @param dishesList
     * @param time
     * @throws ParseException
     */
    public void makeOrder(String userName ,String shopName, String dishesList, String time) throws ParseException {
        ShopHistoryorderEntityExample shopHistoryExample = new ShopHistoryorderEntityExample();
        UserHistoryorderEntityExample userHistoryExample = new UserHistoryorderEntityExample();
        ShopHistoryorderEntityExample.Criteria shopHistoryCriteria = shopHistoryExample.createCriteria();
        UserHistoryorderEntityExample.Criteria userHistoryCriteria = userHistoryExample.createCriteria();
        ShopHistoryorderEntity shopHistoryEntity = new ShopHistoryorderEntity();
        UserHistoryorderEntity userHistoryEntity = new UserHistoryorderEntity();
        SimpleDateFormat orderTimeFormat = new SimpleDateFormat("YY-MM-DD");
        Date orderTime = orderTimeFormat.parse(time);
        userHistoryEntity.setOrdertime(orderTime);
        String[] dishes = dishesList.split(",");
        for (String item : dishes) {
            shopHistoryCriteria.andShopnameEqualTo(shopName).andShopdishesEqualTo(item);
            userHistoryCriteria.andUsernameEqualTo(userName).andShopdishesEqualTo(item).andShopnameEqualTo(shopName);
            int shopHistoryCount = shopHistoryMapper.countByExample(shopHistoryExample);
            int userHistoryCount = userHistoryMapper.countByExample(userHistoryExample);
            if (shopHistoryCount != 0) {
                shopHistoryEntity = shopHistoryMapper.selectByExample(shopHistoryExample).get(0);
                shopHistoryEntity.setMonthcount(shopHistoryEntity.getMonthcount() + 1);
                shopHistoryMapper.updateByExampleSelective(shopHistoryEntity, shopHistoryExample);
            }else {
                shopHistoryEntity.setShopname(shopName);
                shopHistoryEntity.setShopdishes(item);
                shopHistoryEntity.setMonthcount(shopHistoryEntity.getMonthcount() + 1);
                shopHistoryMapper.insertSelective(shopHistoryEntity);
            }
            if (userHistoryCount != 0) {
                userHistoryEntity = userHistoryMapper.selectByExample(userHistoryExample).get(0);
                userHistoryEntity.setCount(userHistoryEntity.getCount() + 1);
                userHistoryMapper.updateByExampleSelective(userHistoryEntity, userHistoryExample);
            }else {
                userHistoryEntity.setUsername(userName);
                userHistoryEntity.setShopname(shopName);
                userHistoryEntity.setShopdishes(item);
                userHistoryEntity.setCount(userHistoryEntity.getCount() + 1);
                userHistoryMapper.insertSelective(userHistoryEntity);
            }
            userHistoryEntity.setShopdishes(item);
            userHistoryMapper.insertSelective(userHistoryEntity);
        }
    }

    /**
     * 个人用户查询历史订单
     */
    public List<UserHistoryorderEntity> searchHistoryOrder(String userName) {
        List<UserHistoryorderEntity> userHistoryOrder;
        UserHistoryorderEntityExample userHistoryExample = new UserHistoryorderEntityExample();
        UserHistoryorderEntityExample.Criteria criteria = userHistoryExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        userHistoryOrder = userHistoryMapper.selectByExample(userHistoryExample);
        return userHistoryOrder;
    }

}
