package com.xjx.example.dao;

import com.xjx.example.entity.User;
import com.xjx.example.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {


    //通过用户名查询用户
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                    User user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
        }
        return null;
    }


    //注册新用户
    public User registerUser(User user) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
            pstmt.executeUpdate();
            //返回含有userId的User对象
            return getUserByUsername(user.getUsername());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }

}
