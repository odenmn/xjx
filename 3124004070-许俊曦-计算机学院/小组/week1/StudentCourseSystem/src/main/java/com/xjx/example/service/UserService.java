package com.xjx.example.service;

import com.xjx.example.dao.UserDao;
import com.xjx.example.entity.User;

public class UserService {
    private UserDao userDao = new UserDao();

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public User registerUser(User user) {
        return userDao.registerUser(user);
    }

    public boolean login(String username, String password) {
        User user = getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
