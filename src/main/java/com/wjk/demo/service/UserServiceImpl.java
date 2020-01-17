package com.wjk.demo.service;

import com.wjk.demo.mapper.UserMapper;
import com.wjk.demo.pojo.Business;
import com.wjk.demo.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUser(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void saveUser(User user) {
        user.setPhoto_register(0);
      userMapper.insert(user);
    }

    @Override
    public User checkLogin(String username, String password) {
           User user= userMapper.findByUsernameAndPassword(username,password);
        return user;
    }

    @Override
    public void updateById(User user) {
        userMapper.updateById(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return userMapper.findUserByUserName(username);
    }


}
