package com.example.demo.utils;

/**
 * Created by liubaoshuai_i on 2018/4/11.
 */
public class CommonException extends Exception {

    public CommonException() {
        super();
    }

    public CommonException(String msg){
        super(msg);
    }

    public CommonException(String msg, Throwable cause){
        super(msg, cause);
    }

    public CommonException(Throwable cause){
        super(cause);
    }

    protected CommonException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
