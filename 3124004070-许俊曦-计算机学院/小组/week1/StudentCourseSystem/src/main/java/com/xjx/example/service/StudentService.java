package com.xjx.example.service;

import com.xjx.example.dao.StudentDao;
import com.xjx.example.entity.Student;

import java.util.List;

public class StudentService {
    private StudentDao studentDao = new StudentDao();


    //得到students表中的全部信息
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }


    //添加学生
    public void addStudent(Student student){
        studentDao.addStudent(student);
    }


    //更新学生的手机号码
    public void updateStudentPhone(int studentId, String phone) {
        studentDao.updateStudentPhone(studentId, phone);
    }


    //通过studentId查询学生
    public Student getStudentById(int studentId){
        return studentDao.getStudentById(studentId);
    }


    //通过学生的name返回Student对象
    public Student getstudentByName(String name){
        return studentDao.getStudentByName(name);
    }
}
