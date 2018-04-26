package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.CommonException;
import com.example.demo.utils.HttpUtils;
import com.example.demo.utils.SimpleResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于集中处理异常
 * Created by liubaoshuai_i on 2018/4/11.
 */
public class BaseController {

    @ExceptionHandler
    public void exception(Exception ex, HttpServletRequest req, HttpServletResponse resp){
        SimpleResult simpleResult = new SimpleResult();
        simpleResult.setSuccess(false);
        if (ex instanceof CommonException) {
            simpleResult.setMsg(ex.getMessage());
        }
        HttpUtils.writeJsonStr(resp, JSONObject.toJSONString(simpleResult));
    }
}
