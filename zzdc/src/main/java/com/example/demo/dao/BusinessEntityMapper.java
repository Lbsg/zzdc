package com.example.demo.dao;

import com.example.demo.entity.BusinessEntity;
import com.example.demo.entity.BusinessEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_entity
     *
     * @mbggenerated
     */
    int countByExample(BusinessEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_entity
     *
     * @mbggenerated
     */
    int deleteByExample(BusinessEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_entity
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_entity
     *
     * @mbggenerated
     */
    int insert(BusinessEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_entity
     *
     * @mbggenerated
     */
    int insertSelective(BusinessEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_entity
     *
     * @mbggenerated
     */
    List<BusinessEntity> selectByExample(BusinessEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_entity
     *
     * @mbggenerated
     */
    BusinessEntity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_entity
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BusinessEntity record, @Param("example") BusinessEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_entity
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BusinessEntity record, @Param("example") BusinessEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_entity
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BusinessEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_entity
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BusinessEntity record);
}