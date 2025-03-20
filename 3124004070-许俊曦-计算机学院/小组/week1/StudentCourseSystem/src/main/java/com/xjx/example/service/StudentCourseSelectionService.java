package com.xjx.example.service;

import com.xjx.example.dao.StudentCourseSelectionDao;
import com.xjx.example.entity.Course;
import com.xjx.example.entity.StudentCourseSelection;

import java.util.List;

public class StudentCourseSelectionService {
    private StudentCourseSelectionDao studentCourseSelectionDao = new StudentCourseSelectionDao();

    //查询某学生的选课情况
    public List<StudentCourseSelection> getCoursesByStudentId(int studentId) {
        return studentCourseSelectionDao.getCoursesByStudentId(studentId);
    }


    //查询某课程的学生名单
    public List<StudentCourseSelection> getStudentByCourseId(int courseId) {
        return studentCourseSelectionDao.getStudentByCourseId(courseId);
    }


    //学生选课
    public void addStudentCourse(int studentId, int courseId) {
        studentCourseSelectionDao.addStudentCourse(studentId,courseId);
    }


    //学生退课
    public void deleteStudentCourse(int studentId, int courseId) {
        studentCourseSelectionDao.deleteStudentCourse(studentId, courseId);
    }


}
