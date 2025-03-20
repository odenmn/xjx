package com.xjx.example.entity;

public class StudentCourseSelection {
    private int id;
    private int studentId;
    private int courseId;

    public StudentCourseSelection(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public StudentCourseSelection() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
