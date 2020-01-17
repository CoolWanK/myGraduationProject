package com.wjk.demo.mapper;


import com.wjk.demo.pojo.Orders;
import com.wjk.demo.pojo.OrdersGoods;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdersMapper {
 Integer insertOrders(Orders orders);
 void insertorders_id(List<OrdersGoods> list);
 List<Orders> findAllOrders(Integer business_id);
 List<Orders> findAllOrdersByUserId(Integer user_id);
 void updateOrders(Orders orders);
 Orders findOrdersById(Integer id);
 List<Orders> findAllYesOrders(Integer business_id);
 List<Orders> findAllNoOrders(Integer business_id);

}