package com.example.demo.entity;

public class ShopHistoryorderEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_historyorder_entity.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_historyorder_entity.shopname
     *
     * @mbggenerated
     */
    private String shopname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_historyorder_entity.shopdishes
     *
     * @mbggenerated
     */
    private String shopdishes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_historyorder_entity.monthcount
     *
     * @mbggenerated
     */
    private Integer monthcount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_historyorder_entity.id
     *
     * @return the value of shop_historyorder_entity.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_historyorder_entity.id
     *
     * @param id the value for shop_historyorder_entity.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_historyorder_entity.shopname
     *
     * @return the value of shop_historyorder_entity.shopname
     *
     * @mbggenerated
     */
    public String getShopname() {
        return shopname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_historyorder_entity.shopname
     *
     * @param shopname the value for shop_historyorder_entity.shopname
     *
     * @mbggenerated
     */
    public void setShopname(String shopname) {
        this.shopname = shopname == null ? null : shopname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_historyorder_entity.shopdishes
     *
     * @return the value of shop_historyorder_entity.shopdishes
     *
     * @mbggenerated
     */
    public String getShopdishes() {
        return shopdishes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_historyorder_entity.shopdishes
     *
     * @param shopdishes the value for shop_historyorder_entity.shopdishes
     *
     * @mbggenerated
     */
    public void setShopdishes(String shopdishes) {
        this.shopdishes = shopdishes == null ? null : shopdishes.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_historyorder_entity.monthcount
     *
     * @return the value of shop_historyorder_entity.monthcount
     *
     * @mbggenerated
     */
    public Integer getMonthcount() {
        return monthcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_historyorder_entity.monthcount
     *
     * @param monthcount the value for shop_historyorder_entity.monthcount
     *
     * @mbggenerated
     */
    public void setMonthcount(Integer monthcount) {
        this.monthcount = monthcount;
    }
}