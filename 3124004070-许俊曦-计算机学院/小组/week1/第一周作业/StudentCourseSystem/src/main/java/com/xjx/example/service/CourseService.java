package com.xjx.example.service;

import com.xjx.example.dao.CourseDao;
import com.xjx.example.entity.Course;

import java.util.List;

public class CourseService {
    private CourseDao courseDao = new CourseDao();


    //得到courses表中的全部信息
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }


    //添加课程
    public void addCourse(Course course) {
        courseDao.addCourse(course);
    }


    //删除课程
    public void deleteCourse(int courseId){
        courseDao.deleteCourse(courseId);
    }


    //通过courseId查询课程
    public Course getCoursesById(int courseId) {
        return courseDao.getCourseById(courseId);
    }
}
