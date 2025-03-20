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
import java.util.stream.Collectors;

public class CourseDao {
    public List<Course> getAllCourses(){
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement prtmt = conn.prepareStatement(sql);
             ResultSet rs = prtmt.executeQuery()){
            while (rs.next()){
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

    public void addCourse(Course course){
        String sql = "INSERT INTO courses (course_name,credit,teacher,description) VALUES (?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,course.getCourseName());
            pstmt.setBigDecimal(2,course.getCredit());
            pstmt.setString(3,course.getTeacher());
            pstmt.setString(4,course.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse(int courseId) {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setInt(1, courseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        Course course = null;
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 先设置参数
            pstmt.setInt(1, courseId);
            // 再执行查询
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    course = new Course();
                    course.setCourseId(rs.getInt("course_id"));
                    course.setCourseName(rs.getString("course_name"));
                    course.setCredit(rs.getBigDecimal("credit"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }
}
