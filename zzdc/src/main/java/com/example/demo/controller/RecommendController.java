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
    public void getRecommend(String user_profile, String item_profile, HttpServletRequest req, HttpServletResponse resp) {
        ResultPages rs = new ResultPages();
        double[] user = {0.5, 0.4, 0.7, 1, 1};
        double[] one = {0, 0, 0, 1, 0};
        double[] two = {1, 1, 0, 1, 0};
        double[] three = {1, 1, 1, 1, 1};
        double[] four = {0, 1, 1, 1, 0};
        double[] five = {0, 0, 0, 0, 0};
        Map<String, double[]> test = new HashMap<>();
        test.put("土豆", one);
        test.put("白菜", two);
        test.put("萝卜", three);
        test.put("倭瓜", four);
        test.put("西红柿", five);
        List<String> reList = recommendService.baseCBRecommend(user, test);
        rs.setSuccess(true);
        rs.setAaData(reList);
        HttpUtils.writeHttpServletResponse(resp, rs);
    }
}
