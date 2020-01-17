package com.wjk.demo.service;

import com.wjk.demo.pojo.Business;
import com.wjk.demo.pojo.Tag;
import com.wjk.demo.pojo.Type;

import java.util.List;

public interface BusinessService {
    Integer insert(Business business);
    Business findByPassword(String username,String password);
    void insertType(Integer typeid,Integer id);
    Business getType(Integer id);
    void update(Business business);
    Business findByNickname(String nickname);
    List<String> findAllUsername();
    List<String>findAllNickname();
    List<Business>findAllBusiness();
    void updateFlag(Business business);
    List<Business>findFlagBusiness();

    List<Tag> findTagByBusinessId(Integer id);
    Business findBusinessById(Integer id);
    Type findBusinessByTypeId(Integer id);
}
