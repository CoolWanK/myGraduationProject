package com.wjk.demo.mapper;


import com.wjk.demo.pojo.Business;
import com.wjk.demo.pojo.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    void insertGoods(Goods goods);

    Business findAllGoods(Integer id);

    Goods findGoodsById(Integer id);

    void updateGoods(Goods goods);

    void deleteGoodsById(Integer id);

    List<String>findAllGoodsNameByBusinessId(Integer id);
    List<Goods>findBySearchName(String search);
    List<Goods> findFiveGoods();
}