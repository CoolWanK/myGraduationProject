package com.wjk.demo.pojo;
import java.util.Date;
import java.util.List;

public class Orders {
    private Integer  id;
    private List<Goods> goodsList;
    private  Integer business_id;
    private Integer user_id;
    private Integer goods_id;
    private Business business;
    private User user;
    private Date createTime;
    private Date user_time;
    private Date business_time;
    private Integer business_order;

    public Integer getBusiness_order() {
        return business_order;
    }

    public void setBusiness_order(Integer business_order) {
        this.business_order = business_order;
    }

    public Date getUser_time() {
        return user_time;
    }

    public void setUser_time(Date user_time) {
        this.user_time = user_time;
    }

    public Date getBusiness_time() {
        return business_time;
    }

    public void setBusiness_time(Date business_time) {
        this.business_time = business_time;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", goodsList=" + goodsList +
                ", business_id=" + business_id +
                ", user_id=" + user_id +
                ", goods_id=" + goods_id +
                ", business=" + business +
                ", user=" + user +
                ", createTime=" + createTime +
                '}';
    }
}
