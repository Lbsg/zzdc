package com.example.demo.utils;

/**
 * 处理简单的Http返回结果
 * Created by liubaoshuai_i on 2018/4/11.
 */
public class SimpleResult {

    private String msg;
    private boolean success;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
