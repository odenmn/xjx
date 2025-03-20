package com.xjx.example.entity;

public class Student {
    private int studentId;
    private int userId;
    private String name;
    private String phone;

    public Student(int studentId, int userId, String name, String phone) {
        this.studentId = studentId;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
    }

    public Student() {
    }

    public Student(int userId, String name, String phone) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
