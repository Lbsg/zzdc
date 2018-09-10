package com.example.demo.controller;

import com.example.demo.service.InfoManageService;
import com.example.demo.utils.CommonException;
import com.example.demo.utils.HttpUtils;
import com.example.demo.utils.ResultPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/BaseInfoAction")
public class BaseInfoController {

    @Autowired
    private InfoManageService infoService;

    /**
     * 展示单个个人商家或用户信息
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public void showInfo(String name, String type, HttpServletRequest req, HttpServletResponse resp){
        List<?> showList = infoService.search(name, type);
        ResultPages rs = new ResultPages();
        rs.setAaData(showList);
        rs.setSuccess(true);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }

    /**
     * 个人用户和商家用户注册账户
     * @param name
     * @param sex
     * @param phone
     * @param address
     * @param password
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerUserOrBusiness(String name, String phone, String address, int sex, String password, String type, HttpServletRequest request, HttpServletResponse response) throws CommonException, InterruptedException{
        int registerResult = infoService.createUserOrBusiness(name, phone, address, sex, password, type);
        ResultPages rs = new ResultPages();
        rs.setRecordsTotal(registerResult);
        if (registerResult > 0){
            rs.setSuccess(true);
            rs.setMsg("注册成功!");
        }else {
            rs.setSuccess(false);
            rs.setMsg("注册失败");
        }
        HttpUtils.writeHttpServletResponse(response, rs);
    }

    /**
     * 个人用户和商家用户注销账户
     * @param name
     * @param type
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void delUserOrBusiness(String name, String type, HttpServletRequest req, HttpServletResponse resp){
        int delResult = infoService.delUserOrBusiness(name, type);
        ResultPages rs = new ResultPages();
        rs.setRecordsTotal(delResult);
        if (delResult > 0){
            rs.setSuccess(true);
            rs.setMsg("注销成功!");
        }else {
            rs.setSuccess(false);
            rs.setMsg("注销失败!");
        }
        HttpUtils.writeHttpServletResponse(resp, rs);
    }

    /**
     * 个人用户或商家用户更改信息
     * @param name
     * @param phone
     * @param address
     * @param password
     * @param type
     */
    @RequestMapping(value = "/editInfo", method = RequestMethod.POST)
    public void editUserOrBusiness(String name, String phone, String address, String password, String type, HttpServletRequest req, HttpServletResponse resp){
        int editResult = infoService.editUserOrBusinessInfo(name, phone, address, password, type);
        ResultPages rs = new ResultPages();
        rs.setRecordsTotal(editResult);
        if (editResult > 0){
            rs.setSuccess(true);
            rs.setMsg("修改成功!");
        }else {
            rs.setSuccess(false);
            rs.setMsg("修改失败!");
        }
        HttpUtils.writeHttpServletResponse(resp, rs);
    }
}
