package com.xjx.example.service;

import com.xjx.example.dao.StudentDao;
import com.xjx.example.entity.Student;

import java.util.List;

public class StudentService {
    private StudentDao studentDao = new StudentDao();

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public void addStudent(Student student){
        studentDao.addStudent(student);
    }
    public void updateStudentPhone(int studentId, String phone) {
        studentDao.updateStudentPhone(studentId, phone);
    }

    public Student getStudentById(int studentId){
        return studentDao.getStudentById(studentId);
    }
}
