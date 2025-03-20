package com.xjx.example.service;

import com.xjx.example.dao.UserDao;
import com.xjx.example.entity.User;

public class UserService {
    private UserDao userDao = new UserDao();


    //通过用户名查询用户
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }


    //注册新用户
    public User registerUser(User user) {
        return userDao.registerUser(user);
    }


    //判断是否成功登录
    public boolean login(String username, String password) {
        User user = getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
