package com.wjk.demo.mapper;


import com.wjk.demo.pojo.Business;
import com.wjk.demo.pojo.Tag;
import com.wjk.demo.pojo.Type;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BusinessMapper {
    Integer insert(Business business);
    Business findBusinessByPasswrrd(String username,String password);
    void insertType(Integer typeid,Integer id);
    Business businessGetType(Integer id);
    void update(Business business);
    Business findByNickname(String nickname);
    List<String>findAllUsername();
    List<String>findAllNickname();
    List<Business>findAllBusiness();
    void updateFlag(Business business);
    List<Business>findFlagBusiness();
    List<Tag> findTagByBusinessId(Integer id);
    Business findBusinessById(Integer id);
    Type findBusinessByTypeId(Integer id);
}