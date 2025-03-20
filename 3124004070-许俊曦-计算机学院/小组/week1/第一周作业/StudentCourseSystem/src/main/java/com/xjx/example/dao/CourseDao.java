package com.xjx.example.dao;

import com.xjx.example.entity.Course;
import com.xjx.example.entity.Student;
import com.xjx.example.util.JDBCUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {

    //得到courses表中的全部信息
    public List<Course> getAllCourses(){
        List<Course> courses = new ArrayList<>();//courses集合
        String sql = "SELECT * FROM courses";//查询
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement prtmt = conn.prepareStatement(sql);
             ResultSet rs = prtmt.executeQuery()){
            while (rs.next()){
                //遍历获得每个course对象
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                BigDecimal credit = rs.getBigDecimal("credit");
                String teacher = rs.getString("teacher");
                String description = rs.getString("description");
                courses.add(new Course(courseId,courseName,credit,teacher,description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }


    //添加课程
    public void addCourse(Course course){
        String sql = "INSERT INTO courses (course_name,credit,teacher,description) VALUES (?,?,?,?)";//插入
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            //赋值
            pstmt.setString(1,course.getCourseName());
            pstmt.setBigDecimal(2,course.getCredit());
            pstmt.setString(3,course.getTeacher());
            pstmt.setString(4,course.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //删除课程
    public void deleteCourse(int courseId) {
        String sql = "DELETE FROM courses WHERE course_id = ?";//删除
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setInt(1, courseId);//赋值
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //通过courseId查询课程
    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM courses WHERE course_id = ?";//查询
        Course course = null;
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //赋值，先执行sql语句
            pstmt.setInt(1, courseId);
            // 再执行查询
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    course = new Course();
                    course.setCourseId(rs.getInt("course_id"));
                    course.setCourseName(rs.getString("course_name"));
                    course.setCredit(rs.getBigDecimal("credit"));
                    course.setTeacher(rs.getString("teacher"));
                    course.setDescription(rs.getString("description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }
}
