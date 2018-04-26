package com.example.demo.controller;

import com.example.demo.utils.CommonException;
import com.example.demo.service.UserActionService;
import com.example.demo.utils.HttpUtils;
import com.example.demo.utils.ResultPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

/**
 * Created by liubaoshuai_i on 2018/4/11.
 */
@RestController
@RequestMapping("/UserAction")
public class UserActionController extends BaseController{

    @Autowired
    private UserActionService actionService;

    /**
     * 个人用户登录
     * @param name
     * @param password
     * @param req
     * @param resp
     * @throws CommonException
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(String name, String password, HttpServletRequest req, HttpServletResponse resp) throws CommonException {
        boolean isPass = actionService.login(name, password);
        ResultPages rs = new ResultPages();
        rs.setSuccess(isPass);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }

    /**
     * 个人用户提交订单
     *
     */
    @RequestMapping(value = "/makeOrder", method = RequestMethod.POST)
    public void makeOrder(String userName ,String shopName, String dishesList, String time, HttpServletRequest req, HttpServletResponse resp) throws ParseException {
        ResultPages rs = new ResultPages();
        actionService.makeOrder(userName, shopName, dishesList, time);
        rs.setSuccess(true);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }
}
