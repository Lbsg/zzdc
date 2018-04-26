package com.example.demo.controller;

import com.example.demo.entity.StudentEntity;
import com.example.demo.service.DealStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by liubaoshuai_i on 2018/4/8.
 */
@RestController
@RequestMapping("/students")
public class DealStudentInfoController {
    @Autowired
    private DealStudentInfoService stuService;

    @RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
    public List<StudentEntity> getStudents(){
        List<StudentEntity> stuList = stuService.getAllStudents();
        return stuList;
    }
}
