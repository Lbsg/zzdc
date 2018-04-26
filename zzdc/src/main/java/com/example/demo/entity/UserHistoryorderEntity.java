package com.example.demo.entity;

import java.util.Date;

public class UserHistoryorderEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_historyorder_entity.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_historyorder_entity.username
     *
     * @mbggenerated
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_historyorder_entity.shopname
     *
     * @mbggenerated
     */
    private String shopname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_historyorder_entity.shopdishes
     *
     * @mbggenerated
     */
    private String shopdishes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_historyorder_entity.ordertime
     *
     * @mbggenerated
     */
    private Date ordertime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_historyorder_entity.count
     *
     * @mbggenerated
     */
    private Integer count;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_historyorder_entity.id
     *
     * @return the value of user_historyorder_entity.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_historyorder_entity.id
     *
     * @param id the value for user_historyorder_entity.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_historyorder_entity.username
     *
     * @return the value of user_historyorder_entity.username
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_historyorder_entity.username
     *
     * @param username the value for user_historyorder_entity.username
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_historyorder_entity.shopname
     *
     * @return the value of user_historyorder_entity.shopname
     *
     * @mbggenerated
     */
    public String getShopname() {
        return shopname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_historyorder_entity.shopname
     *
     * @param shopname the value for user_historyorder_entity.shopname
     *
     * @mbggenerated
     */
    public void setShopname(String shopname) {
        this.shopname = shopname == null ? null : shopname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_historyorder_entity.shopdishes
     *
     * @return the value of user_historyorder_entity.shopdishes
     *
     * @mbggenerated
     */
    public String getShopdishes() {
        return shopdishes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_historyorder_entity.shopdishes
     *
     * @param shopdishes the value for user_historyorder_entity.shopdishes
     *
     * @mbggenerated
     */
    public void setShopdishes(String shopdishes) {
        this.shopdishes = shopdishes == null ? null : shopdishes.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_historyorder_entity.ordertime
     *
     * @return the value of user_historyorder_entity.ordertime
     *
     * @mbggenerated
     */
    public Date getOrdertime() {
        return ordertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_historyorder_entity.ordertime
     *
     * @param ordertime the value for user_historyorder_entity.ordertime
     *
     * @mbggenerated
     */
    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_historyorder_entity.count
     *
     * @return the value of user_historyorder_entity.count
     *
     * @mbggenerated
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_historyorder_entity.count
     *
     * @param count the value for user_historyorder_entity.count
     *
     * @mbggenerated
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}