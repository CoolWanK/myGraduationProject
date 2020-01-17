package com.wjk.demo.service;

import com.wjk.demo.mapper.GoodsMapper;
import com.wjk.demo.pojo.Business;
import com.wjk.demo.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public void insertGoods(Goods goods) {
        goods.setCreateTime(new Date());
        goodsMapper.insertGoods(goods);
    }

    @Override
    public Business findAllGoods(Integer id) {
        return goodsMapper.findAllGoods(id);
    }

    @Override
    public Goods findGoodsById(Integer id) {
        return goodsMapper.findGoodsById(id);
    }

    @Transactional
    @Override
    public void updateGoods(Goods goods) {
        goodsMapper.updateGoods(goods);
    }

    @Override
    public void deleteGoodsById(Integer id) {
        if (id!=null){
            goodsMapper.deleteGoodsById(id);
        }
    }

    @Override
    public List<String> findAllGoodsNameByBusinessId(Integer id) {
        return goodsMapper.findAllGoodsNameByBusinessId(id);
    }

    @Override
    public List<Goods> findBySearchName(String search) {
        return goodsMapper.findBySearchName(search);
    }

    @Override
    public List<Goods> findFiveGoods() {
        return goodsMapper.findFiveGoods();
    }
}
