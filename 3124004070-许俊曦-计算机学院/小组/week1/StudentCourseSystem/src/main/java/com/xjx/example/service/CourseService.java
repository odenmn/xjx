package com.xjx.example.service;

import com.xjx.example.dao.CourseDao;
import com.xjx.example.entity.Course;

import java.util.List;

public class CourseService {
    private CourseDao courseDao = new CourseDao();

    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public void addCourse(Course course) {
        courseDao.addCourse(course);
    }

    public void deleteCourse(int courseId){
        courseDao.deleteCourse(courseId);
    }

    public Course getCoursesById(int courseId) {
        return courseDao.getCourseById(courseId);
    }
}
