package com.wjk.demo.service;

import com.wjk.demo.pojo.Business;
import com.wjk.demo.pojo.Type;

import java.util.List;

public interface TypeService {
    List<Type> findAll();
    List<Business>findBusinessByTypeName(String name);
}
