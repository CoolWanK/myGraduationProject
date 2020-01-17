package com.wjk.demo.service;

import com.wjk.demo.mapper.OrdersMapper;
import com.wjk.demo.pojo.Goods;
import com.wjk.demo.pojo.Orders;
import com.wjk.demo.pojo.OrdersGoods;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Transactional
    @Override
    public void insertOrders(Orders orders) {
        List<OrdersGoods> list=new ArrayList<>();
        ordersMapper.insertOrders(orders);
        Integer id=orders.getId();
        if (id!=null){
            System.out.println(id);
        }else{
            System.out.println("id出异常了");
        }

        List<Goods>goodsList=orders.getGoodsList();
        for (Goods g:goodsList){
            OrdersGoods ordersGoods=new OrdersGoods();
            ordersGoods.setOrders_id(id);
            ordersGoods.setGoods_id(g.getId());
            list.add(ordersGoods);
        }
        ordersMapper.insertorders_id(list);
    }

    @Override
    public List<Orders> findAllOrders(Integer business_id) {
        return ordersMapper.findAllOrders(business_id);
    }

    @Override
    public List<Orders> findAllOrdersByUserId(Integer user_id) {
        return ordersMapper.findAllOrdersByUserId(user_id);
    }
    @Transactional
    @Override
    public void updateOrders(Integer id) {
        Orders orders=ordersMapper.findOrdersById(id);
        orders.setBusiness_time(new Date());
        System.out.println(orders.getBusiness_time()+"     "+orders.getUser_time());
        ordersMapper.updateOrders(orders);
    }

    @Override
    public Orders findOrdersById(Integer id) {
        return ordersMapper.findOrdersById(id);
    }

    @Override
    public List<Orders> findAllYesOrders(Integer business_id) {
        return ordersMapper.findAllYesOrders(business_id);
    }

    @Override
    public List<Orders> findAllNoOrders(Integer business_id) {
        return ordersMapper.findAllNoOrders(business_id);
    }

    @Override
    public void updateOrderByOrder(Orders orders) {
        ordersMapper.updateOrders(orders);
    }
}
