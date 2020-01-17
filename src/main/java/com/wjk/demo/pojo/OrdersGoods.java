package com.wjk.demo.pojo;
public class OrdersGoods {
    private Integer orders_id;
    private Integer goods_id;

    public Integer getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(Integer orders_id) {
        this.orders_id = orders_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    @Override
    public String toString() {
        return "OrdersGoods{" +
                "orders_id=" + orders_id +
                ", goods_id=" + goods_id +
                '}';
    }
}
