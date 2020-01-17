package com.wjk.demo.service;

import com.wjk.demo.mapper.TypeMapper;
import com.wjk.demo.pojo.Business;
import com.wjk.demo.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public List<Type> findAll() {
        return typeMapper.getTypeList();
    }

    @Override
    public List<Business> findBusinessByTypeName(String name) {
        List<Business>list=typeMapper.findBusinessByTypeName(name);

        return list;
    }
}
