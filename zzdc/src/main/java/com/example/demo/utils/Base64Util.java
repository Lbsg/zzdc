package com.example.demo.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * Created by liubaoshuai_i on 2018/4/10.
 * 选取简单加密算法对用户登录时密码进行保护
 */
public class Base64Util {

    /**
     * base64加密
     * @param data
     * @return
     */
    public static String base64Encrypt(byte[] data){
        String result = new BASE64Encoder().encode(data);
        return result;
    }

    /**
     * base64解密
     * @param data
     * @return
     * @throws Exception
     */
    public static String base64Decrypt(String data) throws Exception{
        byte[] resultBytes = new BASE64Decoder().decodeBuffer(data);
        return new String(resultBytes);
    }
}
