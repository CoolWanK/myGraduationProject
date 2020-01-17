package com.wjk.demo.service;

import com.wjk.demo.mapper.BusinessMapper;
import com.wjk.demo.pojo.Business;
import com.wjk.demo.pojo.Goods;
import com.wjk.demo.pojo.Tag;
import com.wjk.demo.pojo.Type;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;


@Service
public class BusinessServiceImpl implements  BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public Integer insert(Business business) {
        return businessMapper.insert(business);

    }

    @Override
    public Business findByPassword(String username, String password) {

        return businessMapper.findBusinessByPasswrrd(username,password);
    }

    @Override
    public void insertType(Integer typeid,Integer id) {
            if (typeid!=null){
                businessMapper.insertType(typeid,id);
            }
    }

    @Override
    public Business getType(Integer id) {
        return businessMapper.businessGetType(id);
    }

    @Override
    public void update(Business business) {
        businessMapper.update(business);
    }

    @Override
    public Business findByNickname(String nickname) {
        return businessMapper.findByNickname(nickname);
    }

    @Override
    public List<String> findAllUsername() {
        return businessMapper.findAllUsername();
    }

    @Override
    public List<String> findAllNickname() {
        return businessMapper.findAllNickname();
    }


    @Override
    public List<Business> findAllBusiness() {
        List<Business>list=businessMapper.findAllBusiness();
       return list;
    }

    @Override
    public void updateFlag(Business business) {
        businessMapper.updateFlag(business);
    }

    @Override
    public List<Business> findFlagBusiness() {


        return  businessMapper.findFlagBusiness();
    }


    @Override
    public List<Tag> findTagByBusinessId(Integer id) {
        return businessMapper.findTagByBusinessId(id);
    }

    @Override
    public Business findBusinessById(Integer id) {
        return businessMapper.findBusinessById(id);
    }

    @Override
    public Type findBusinessByTypeId(Integer id) {
        return businessMapper.findBusinessByTypeId(id);
    }

}
