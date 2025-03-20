package com.xjx.example.dao;

import com.xjx.example.entity.Course;
import com.xjx.example.entity.StudentCourseSelection;
import com.xjx.example.util.JDBCUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseSelectionDao {

    //管理员查询学生的选课情况
    public List<StudentCourseSelection> getCoursesByStudentId(int studentId){
        List<StudentCourseSelection> studentCourses = new ArrayList<>();
        String sql = "SELECT * FROM student_courses WHERE student_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,studentId);
            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    StudentCourseSelection scs = new StudentCourseSelection();
                    scs.setStudentId(rs.getInt("student_id"));
                    scs.setCourseId(rs.getInt("course_id"));
                    studentCourses.add(scs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentCourses;
    }

    //管理员查询课程的学生名单
    public List<StudentCourseSelection> getStudentByCourseId(int courseId){
        List<StudentCourseSelection> studentCourses = new ArrayList<>();
        String sql = "SELECT * FROM student_courses WHERE course_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, courseId);
            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    StudentCourseSelection scs = new StudentCourseSelection();//查询对象
                    scs.setStudentId(rs.getInt("student_id"));
                    scs.setCourseId(rs.getInt("course_id"));
                    studentCourses.add(scs);//添加进入集合中
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentCourses;
    }

    //学生选课
    public void addStudentCourse(StudentCourseSelection sc){
        String sql = "INSERT INTO student_courses (student_id, course_id) VALUES (?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setInt(1,sc.getStudentId());
            pstmt.setInt(2,sc.getCourseId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //学生退课
    public void deleteStudentCourse(int studentId, int courseId) {
        String sql = "DELETE FROM student_courses WHERE student_id = ? AND course_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
