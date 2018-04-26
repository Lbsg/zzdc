package com.example.demo.controller;

import com.example.demo.entity.ShopDishesEntity;
import com.example.demo.entity.ShopEntity;
import com.example.demo.entity.ShopHistoryorderEntity;
import com.example.demo.service.BusinessActionService;
import com.example.demo.utils.CommonException;
import com.example.demo.utils.HSSFUtils;
import com.example.demo.utils.HttpUtils;
import com.example.demo.utils.ResultPages;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

/**
 * Created by liubaoshuai_i on 2018/4/12.
 */
@RestController
@RequestMapping("/businessAction")
public class BusinessActionController extends BaseController{

    @Autowired
    private BusinessActionService businessService;

    /**
     * 商家用户登录
     * @param name
     * @param password
     * @param req
     * @param resp
     * @throws CommonException
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(String name, String password, HttpServletRequest req, HttpServletResponse resp) throws CommonException {
        boolean isPass = businessService.login(name, password);
        ResultPages rs = new ResultPages();
        rs.setSuccess(isPass);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }

    /**
     * 商家用户增加店铺
     * @param shopName
     * @param hostName
     * @param shopTopic
     * @param address
     * @param start
     * @param end
     * @param req
     * @param resp
     * @throws CommonException
     */
    @RequestMapping(value = "/addShop", method = RequestMethod.POST)
    public void addShop(String shopName, String hostName, String shopTopic, String address, String start, String end, HttpServletRequest req, HttpServletResponse resp) throws CommonException, ParseException {
        int addCount = businessService.createShop(shopName, hostName, shopTopic, address, start, end);
        ResultPages rs = new ResultPages();
        rs.setRecordsTotal(addCount);
        rs.setSuccess(true);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }

    /**
     * 商家用户修改指定店铺信息
     * @param shopName
     * @param shopTopic
     * @param address
     * @param start
     * @param end
     * @param req
     * @param resp
     * @throws ParseException
     */
    @RequestMapping(value = "/editShop", method = RequestMethod.POST)
    public void editShop(String shopName, String shopTopic, String address, String start, String end, HttpServletRequest req, HttpServletResponse resp) throws ParseException {
        int editCount = businessService.editShop(shopName, shopTopic, address, start, end);
        ResultPages rs = new ResultPages();
        rs.setRecordsTotal(editCount);
        rs.setSuccess(true);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }

    /**
     * 商家用户注销指定商铺
     * @param name
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/delShop", method = RequestMethod.POST)
    public void delShop(String name, HttpServletRequest req, HttpServletResponse resp){
        int delCount = businessService.delShop(name);
        ResultPages rs = new ResultPages();
        rs.setRecordsTotal(delCount);
        rs.setSuccess(true);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }

    /**
     * 商家用户查询指定商铺信息（供展示使用）
     * @param name
     * @param resp
     * @param req
     */
    @RequestMapping(value = "/showShop", method = RequestMethod.GET)
    public void showShop(String name, HttpServletRequest req, HttpServletResponse resp){
        List<ShopEntity> shop = businessService.search(name);
        ResultPages rs = new ResultPages();
        rs.setAaData(shop);
        rs.setSuccess(true);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }

    /**
     * 商家用户增加指定店铺商品
     * @param shopName
     * @param shopDishesName
     * @param price
     * @param style
     * @param req
     * @param resp
     * @throws CommonException
     */
    @RequestMapping(value = "/addDished", method = RequestMethod.POST)
    public void addShopDishes(String shopName, String shopDishesName, String price, String style, String ingredient, HttpServletRequest req, HttpServletResponse resp) throws CommonException {
        int addCount = businessService.addShopDishes(shopName, shopDishesName, price, style, ingredient);
        ResultPages rs = new ResultPages();
        rs.setSuccess(true);
        rs.setRecordsTotal(addCount);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }

    /**
     * 商家用户修改指定商店指定信息
     * @param shopName
     * @param shopDishesName
     * @param price
     * @param style
     * @param resp
     * @param req
     */
    @RequestMapping(value = "/editDishes", method = RequestMethod.POST)
    public void editShopDishes(String shopName, String shopDishesName, String price, String style, String ingredient, HttpServletRequest req, HttpServletResponse resp) {
        int editCount = businessService.editShopDishes(shopName, shopDishesName, price, style, ingredient);
        ResultPages rs = new ResultPages();
        rs.setSuccess(true);
        rs.setRecordsTotal(editCount);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }

    /**
     * 商家用户下架指定商店指定商品
     * @param shopName
     * @param shopDishesName
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/delDished", method = RequestMethod.POST)
    public void delShopDishes(String shopName, String shopDishesName, HttpServletRequest req, HttpServletResponse resp) {
        int delCount = businessService.delShopDishes(shopName, shopDishesName);
        ResultPages rs = new ResultPages();
        rs.setSuccess(true);
        rs.setRecordsTotal(delCount);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }

    /**
     * 商家用户查询指定店铺所有商品
     * @param shopName
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/showDishes", method = RequestMethod.GET)
    public void showShopDishes(String shopName, HttpServletRequest req, HttpServletResponse resp) {
        List<ShopDishesEntity> shopDishesList = businessService.searchShopDishes(shopName);
        ResultPages rs = new ResultPages();
        rs.setAaData(shopDishesList);
        rs.setSuccess(true);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }

    /**
     * 商家用户获取指定店铺月销量展示
     * @param shopName
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/showMonthOrders", method = RequestMethod.GET)
    public void showMonthOrders(String shopName, HttpServletRequest req, HttpServletResponse resp) {
        List<ShopHistoryorderEntity> shopHistoryList = businessService.searchHistoryOrders(shopName);
        ResultPages rs = new ResultPages();
        rs.setAaData(shopHistoryList);
        rs.setSuccess(true);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }

    /**
     * 商家获得指定商店月报表
     * @param shopName
     * @param resp
     * @param req
     * @throws Exception
     */
    @RequestMapping(value = "/getMonthExcel", method = RequestMethod.GET)
    public void getMonthExcel(String shopName, String month, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] title = {"id", "商铺名称", "菜品名称", "销量"};
        HSSFWorkbook hwb = businessService.getExcel(shopName, title, month);
        ResultPages rs = new ResultPages();
        rs.setSuccess(true);
     //   HSSFUtils.downloadFiles(hwb, shopName, resp);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }
}
