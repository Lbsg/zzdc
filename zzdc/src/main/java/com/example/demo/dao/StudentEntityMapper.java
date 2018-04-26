package com.example.demo.dao;

import com.example.demo.entity.StudentEntity;
import com.example.demo.entity.StudentEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_entity
     *
     * @mbggenerated
     */
    int countByExample(StudentEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_entity
     *
     * @mbggenerated
     */
    int deleteByExample(StudentEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_entity
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_entity
     *
     * @mbggenerated
     */
    int insert(StudentEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_entity
     *
     * @mbggenerated
     */
    int insertSelective(StudentEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_entity
     *
     * @mbggenerated
     */
    List<StudentEntity> selectByExample(StudentEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_entity
     *
     * @mbggenerated
     */
    StudentEntity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_entity
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") StudentEntity record, @Param("example") StudentEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_entity
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") StudentEntity record, @Param("example") StudentEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_entity
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StudentEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_entity
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StudentEntity record);
}