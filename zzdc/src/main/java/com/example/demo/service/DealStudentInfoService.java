package com.example.demo.service;

import com.example.demo.dao.StudentEntityMapper;
import com.example.demo.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liubaoshuai_i on 2018/4/8.
 */
@Service
public class DealStudentInfoService {
    @Autowired
    private StudentEntityMapper stuMapper;

    public List<StudentEntity> getAllStudents(){
        List<StudentEntity> stuList = stuMapper.selectByExample(null);
        return stuList;
    }
}
