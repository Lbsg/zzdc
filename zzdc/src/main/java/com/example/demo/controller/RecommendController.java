package com.example.demo.controller;

import com.example.demo.service.RecommendService;
import com.example.demo.utils.HttpUtils;
import com.example.demo.utils.ResultPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by liubaoshuai_i on 2018/4/15.
 */
@RestController
@RequestMapping("/recommend")
public class RecommendController extends BaseController{

    @Autowired
    private RecommendService recommendService;

    @RequestMapping(value = "/itemRecommend", method = RequestMethod.GET)
    public void getRecommend(String userName, HttpServletRequest req, HttpServletResponse resp) {
        ResultPages rs = new ResultPages();
        List<String> reList = recommendService.baseCBRecommend(userName);
        rs.setSuccess(true);
        rs.setAaData(reList);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }
}
