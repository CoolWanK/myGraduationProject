package com.wjk.demo.mapper;

import com.wjk.demo.pojo.User;

import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    void insert(User user);
    User getUserById(Integer id);
    User findByUsernameAndPassword(String username,String password);

    void updateById(User user);
    User findUserByUserName(String username);

}