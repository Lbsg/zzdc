package com.example.demo.service;

import com.example.demo.dao.BusinessEntityMapper;
import com.example.demo.dao.UserEntityMapper;
import com.example.demo.entity.BusinessEntity;
import com.example.demo.entity.BusinessEntityExample;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserEntityExample;
import com.example.demo.utils.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liubaoshuai_i on 2018/4/10.
 */
@Service
public class InfoManageService {

    @Autowired
    private BusinessEntityMapper busMapper;

    @Autowired
    private UserEntityMapper userMapper;

    /**
     * 增加个人用户或商家用户，通过type字段区分
     * @param name
     * @param phone
     * @param address
     * @param sex
     * @param password
     * @param type
     * @return
     */
    public int createUserOrBusiness(String name, String phone, String address, int sex, String password, String type) throws CommonException {
        int insertCount, tag;
        if(type.equals("user")){
            UserEntityExample userExample = new UserEntityExample();
            UserEntityExample.Criteria criteria = userExample.createCriteria();
            criteria.andNameEqualTo(name);
            tag = userMapper.countByExample(userExample);
            if (tag == 0){
                UserEntity user = new UserEntity();
                user.setName(name);
                user.setSex(sex);
                user.setPassword(password);
                user.setPhone(phone);
                insertCount = userMapper.insertSelective(user);
            }else {
                throw new CommonException("用户名已注册!");
            }
        }else {
            BusinessEntityExample businessExample = new BusinessEntityExample();
            BusinessEntityExample.Criteria criteria = businessExample.createCriteria();
            criteria.andNameEqualTo(name);
            tag = busMapper.countByExample(businessExample);
            if (tag == 0){
                BusinessEntity business = new BusinessEntity();
                business.setName(name);
                business.setSex(sex);
                business.setPhone(phone);
                business.setAddres(address);
                business.setPassword(password);
                insertCount = busMapper.insertSelective(business);
            }else {
                throw new CommonException("用户名已注册!");
            }
        }
        return insertCount;
    }

    /**
     * 删除个人用户或商家用户，通过type字段区分
     * @param name
     * @param type
     * @return
     */
    public int delUserOrBusiness(String name, String type){
        int delCount;
        if (type.equals("user")){
            UserEntityExample userExample = new UserEntityExample();
            UserEntityExample.Criteria criteria = userExample.createCriteria();
            criteria.andNameEqualTo(name);
            delCount = userMapper.deleteByExample(userExample);
        }else{
            BusinessEntityExample businessExample = new BusinessEntityExample();
            BusinessEntityExample.Criteria criteria = businessExample.createCriteria();
            criteria.andNameEqualTo(name);
            delCount = busMapper.countByExample(businessExample);
        }
        return delCount;
    }

    /**
     * 修改个人用户信息或商家信息，通过type字段区分
     * @param name
     * @param phone
     * @param address
     * @param sex
     * @param password
     * @param type
     */
    public int editUserOrBusinessInfo(String name, String phone, String address, int sex, String password, String type){
        int editCount;
        String tempSex = String.valueOf(sex);
        if (type.equals("user")){
            UserEntityExample userExample = new UserEntityExample();
            UserEntityExample.Criteria criteria = userExample.createCriteria();
            criteria.andNameEqualTo(name);
            UserEntity user = new UserEntity();
            user.setName(name);
            if (phone != null){
                user.setPhone(phone);
            }
            if (tempSex.equals("")){
                user.setSex(Integer.parseInt(tempSex));
            }
            if (password != null){
                user.setPassword(password);
            }
            editCount = userMapper.updateByExampleSelective(user, userExample);
        }else {
            BusinessEntity business = new BusinessEntity();
            BusinessEntityExample businessExample = new BusinessEntityExample();
            BusinessEntityExample.Criteria criteria = businessExample.createCriteria();
            criteria.andNameEqualTo(name);
            business.setName(name);
            if (phone != null){
                business.setPhone(phone);
            }
            if (tempSex.equals("")){
                business.setSex(Integer.parseInt(tempSex));
            }
            if (address != null){
                business.setAddres(address);
            }
            if (password != null){
                business.setPassword(password);
            }
            editCount = busMapper.updateByExampleSelective(business, businessExample);
        }
        return editCount;
    }

    /**
     * 返回所有用户或商家数量，供注册成功时展示使用
     * @param type
     * @return
     */
    public int countUserOrBusiness(String type){
        int count;
        if (type.equals("user")){
            count = userMapper.countByExample(null);
        }else {
            count = busMapper.countByExample(null);
        }
        return count;
    }

    /**
     * 查询单个用户或单个商家具体信息
     * @param name
     * @param type
     */
    public List<?> search(String name, String type){
        if (type.equals("user")){
            UserEntityExample userExample = new UserEntityExample();
            UserEntityExample.Criteria criteria = userExample.createCriteria();
            criteria.andNameEqualTo(name);
            List<UserEntity> userList = userMapper.selectByExample(userExample);
            return userList;
        }else {
            BusinessEntityExample businessExample = new BusinessEntityExample();
            BusinessEntityExample.Criteria criteria = businessExample.createCriteria();
            criteria.andNameEqualTo(name);
            List<BusinessEntity> businessList = busMapper.selectByExample(businessExample);
            return businessList;
        }
    }
}
