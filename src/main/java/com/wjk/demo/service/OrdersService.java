package com.wjk.demo.service;

import com.wjk.demo.pojo.Orders;
import java.util.List;

public interface OrdersService {
    void insertOrders(Orders orders);
    List<Orders> findAllOrders(Integer business_id);
    List<Orders> findAllOrdersByUserId(Integer user_id);
    void updateOrders(Integer id);
    Orders findOrdersById(Integer id);
    List<Orders> findAllYesOrders(Integer business_id);
    List<Orders> findAllNoOrders(Integer business_id);
    void updateOrderByOrder(Orders orders);
}
