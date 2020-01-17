package com.wjk.demo.service;

import com.wjk.demo.pojo.Business;
import com.wjk.demo.pojo.User;

public interface UserService {
    User getUser(int id);
    void saveUser(User user);
    User checkLogin(String username,String password);
    void updateById(User user);
    User findUserByUserName(String username);

}
