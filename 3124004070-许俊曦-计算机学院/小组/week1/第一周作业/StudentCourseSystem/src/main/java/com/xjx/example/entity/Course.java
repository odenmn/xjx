package com.xjx.example.entity;

import java.math.BigDecimal;

public class Course {
    private int courseId;
    private String courseName;
    private BigDecimal credit;
    private String teacher;
    private String description;
    public Course(int courseId, String courseName, BigDecimal credit, String teacher, String description) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.teacher = teacher;
        this.description = description;
    }

    public Course(String courseName, BigDecimal credit, String teacher, String description) {
        this.courseName = courseName;
        this.credit = credit;
        this.teacher = teacher;
        this.description = description;
    }

    public Course() {

    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
