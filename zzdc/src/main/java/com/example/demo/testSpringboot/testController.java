package com.example.demo.testSpringboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by liubaoshuai_i on 2018/4/7.
 */
@RestController
@RequestMapping("/test")
public class testController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(){
        return "Hello world";
    }

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String sayHi(){
        return "Hi";
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public List<studentEntity> getStudent(){
        return null;
    }
}
