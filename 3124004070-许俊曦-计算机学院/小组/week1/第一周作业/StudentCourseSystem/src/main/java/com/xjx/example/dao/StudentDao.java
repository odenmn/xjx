package com.xjx.example.dao;

import com.xjx.example.entity.Course;
import com.xjx.example.entity.Student;
import com.xjx.example.entity.StudentCourseSelection;
import com.xjx.example.entity.User;
import com.xjx.example.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDao {

    //得到students表中的全部信息
    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement prtmt = conn.prepareStatement(sql);
             ResultSet rs = prtmt.executeQuery()){
            while (rs.next()){
                //遍历获得每个student对象
                int studentId = rs.getInt("student_id");
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                students.add(new Student(studentId, userId, name, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


    //添加学生
    public void addStudent(Student student){
        String sql = "INSERT INTO students (user_id,name, phone) VALUES (?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,student.getUserId());
            pstmt.setString(2,student.getName());
            pstmt.setString(3,student.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //更新学生的手机号码
    public void updateStudentPhone(int studentId, String phone) {
        String sql = "UPDATE students SET phone = ? WHERE student_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, phone);
            pstmt.setInt(2, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //通过studentId查询学生
    public Student getStudentById(int studentId){
        String sql = "SELECT * FROM students WHERE student_id = ?";
        Student student = null;
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            //赋值，先执行sql语句
            pstmt.setInt(1, studentId);
            //再执行查询
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setStudentId(rs.getInt("student_id"));
                    student.setName(rs.getString("name"));
                    student.setPhone(rs.getString("phone"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    //通过name获得Student对象
    public Student getStudentByName(String name){
        String sql = "SELECT * FROM students WHERE name = ?";
        Student student = null;
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            //赋值，先执行sql语句
            pstmt.setString(1, name);
            //再执行查询
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setStudentId(rs.getInt("student_id"));
                    student.setName(rs.getString("name"));
                    student.setPhone(rs.getString("phone"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

}
