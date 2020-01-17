package com.wjk.demo.mapper;


import com.wjk.demo.pojo.Business;
import com.wjk.demo.pojo.Type;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TypeMapper {
    List<Type> getTypeList();
    List<Business>findBusinessByTypeName(String name);
}