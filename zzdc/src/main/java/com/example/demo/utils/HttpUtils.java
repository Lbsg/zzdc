package com.example.demo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liubaoshuai_i on 2018/4/10.
 */
public class HttpUtils {

    public static void writeJsonStr(HttpServletResponse resp, String jsonStr){
        if (resp != null){
            resp.setCharacterEncoding("utf-8");
            resp.addHeader("Access-Control-Allow-Origin", "*");
            resp.setContentType("application/json;charset=utf-8");
            try {
                PrintWriter pw = resp.getWriter();
                pw.write(jsonStr);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeHttpServletResponse(HttpServletResponse resp, ResultPages rs){
        String jsonStr = JSONObject.toJSONString(rs);
        writeJsonStr(resp, jsonStr);
    }
}
