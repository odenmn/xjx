package com.xjx.example.service;

import com.xjx.example.dao.StudentCourseSelectionDao;
import com.xjx.example.entity.Course;
import com.xjx.example.entity.StudentCourseSelection;

import java.util.List;

public class StudentCourseSelectionService {
    private StudentCourseSelectionDao studentCourseSelectionDao = new StudentCourseSelectionDao();

    public List<StudentCourseSelection> getCoursesByStudentId(int studentId) {
        return studentCourseSelectionDao.getCoursesByStudentId(studentId);
    }

    public List<StudentCourseSelection> getStudentByCourseId(int courseId) {
        return studentCourseSelectionDao.getStudentByCourseId(courseId);
    }
    public void addStudentCourse(StudentCourseSelection sc) {
        studentCourseSelectionDao.addStudentCourse(sc);
    }

    public void deleteStudentCourse(int studentId, int courseId) {
        studentCourseSelectionDao.deleteStudentCourse(studentId, courseId);
    }


}
