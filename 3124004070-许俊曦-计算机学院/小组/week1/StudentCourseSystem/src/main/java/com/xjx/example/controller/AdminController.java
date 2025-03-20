package com.xjx.example.controller;

import com.xjx.example.entity.Course;
import com.xjx.example.entity.Student;
import com.xjx.example.entity.StudentCourseSelection;
import com.xjx.example.service.CourseService;
import com.xjx.example.service.StudentCourseSelectionService;
import com.xjx.example.service.StudentService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    Scanner sc = new Scanner(System.in);
    private StudentService studentService =new StudentService();
    private CourseService courseService = new CourseService();
    private StudentCourseSelectionService studentCourseSelectionService = new StudentCourseSelectionService();
    public void showAdminMenu(){
        while (true) {
            System.out.println("===== 管理员菜单 =====");
            System.out.println("1. 查询所有学生");
            System.out.println("2. 修改学生手机号");
            System.out.println("3. 查询所有课程");
            System.out.println("4. 添加课程");
            System.out.println("5. 删除课程");
            System.out.println("6. 查询某课程的学生名单");
            System.out.println("7. 查询某学生的选课情况");
            System.out.println("8. 退出");
            System.out.print("请选择操作（输入 1 - 8）：");
            String command = sc.nextLine();
            switch (command) {
                case "1":
                    viewAllStudents();
                    break;
                case "2":
                    updateStudentPhone();
                    break;
                case "3":
                    viewAllCourses();
                    break;
                case "4":
                    addCourse();
                    break;
                case "5":
                    deleteCourse();
                    break;
                case "6":
                    viewStudentsByCourse();
                    break;
                case "7":
                    viewCoursesByStudent();
                    break;
                case "8":
                    System.out.println("退出管理员菜单。");
                    return;
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }
    }

    private void viewAllStudents() {
        List<Student> students = studentService.getAllStudents();
        System.out.println("=====所有学生信息=====");
        for (Student student : students) {
            System.out.println("学生 ID: " + student.getStudentId() + ", 姓名: " + student.getName() + ", 手机号: " + student.getPhone());
        }
    }

    private void updateStudentPhone(){
        if (studentService.getAllStudents().isEmpty()){
            System.out.println("当前学生列表为空~~");
            return;
        }
        viewAllStudents();
        System.out.print("请输入要修改手机号的学生 ID：");
        int studentId = sc.nextInt();
        sc.nextLine();
        System.out.print("请输入新的手机号：");
        String phone = sc.nextLine();
        studentService.updateStudentPhone(studentId, phone);
        System.out.println("学生手机号修改成功~~");
    }

    private void viewAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        System.out.println("=====所有课程信息=====");
        for (Course course : courses) {
            System.out.println("课程ID：" + course.getCourseId() + " || 课程名称：" + course.getCourseName() +
                        " || 课程学分：" + course.getCredit() + " || 授课老师：" + course.getTeacher() +
                        " || 课程描述：" + course.getDescription()
            );
        }
    }

    private void addCourse(){
        System.out.print("请输入要添加的课程名称：");
        String courseName = sc.next();
        System.out.print("请输入课程学分：");
        BigDecimal credit = sc.nextBigDecimal();
        sc.nextLine();
        System.out.println("请输入授课老师的姓名：");
        String teacher = sc.next();
        System.out.println("请输入对该课程的描述：");
        String description = sc.next();
        Course course = new Course(courseName, credit,teacher,description);
        courseService.addCourse(course);
        System.out.println("课程添加成功~~");
    }

    private void deleteCourse(){
        viewAllCourses();
        System.out.print("请输入要删除的课程 ID：");
        int courseId = sc.nextInt();
        sc.nextLine();
        courseService.deleteCourse(courseId);
        System.out.println("课程删除成功~~");
    }

    private void viewStudentsByCourse(){
        viewAllCourses();
        System.out.print("请输入要查询的课程 ID：");
        int courseId = sc.nextInt();
        sc.nextLine();
        List<StudentCourseSelection> studentCoursesSelections = studentCourseSelectionService.getStudentByCourseId(courseId);
        System.out.println("选修该课程的学生名单：");
        for (StudentCourseSelection scs : studentCoursesSelections) {
            Student student = studentService.getStudentById(scs.getStudentId());
            if (student != null) {
                System.out.println("学生 ID: " + student.getStudentId() + ", 姓名: " + student.getName() + ", 手机号: " + student.getPhone());
            }
        }
    }

    private void viewCoursesByStudent(){
        viewAllStudents();
        System.out.println("请输入要查询的学生 ID：");
        int studentId = sc.nextInt();
        sc.nextLine();
        List<StudentCourseSelection> studentCourseSelections = studentCourseSelectionService.getCoursesByStudentId(studentId);
        System.out.println("该学生的选课情况：");
        for (StudentCourseSelection scs : studentCourseSelections) {
            Course course = courseService.getCoursesById(scs.getCourseId());
            System.out.println("课程ID：" + course.getCourseId() + " || 课程名称：" + course.getCourseName() +
                    " || 课程学分：" + course.getCredit() + " || 授课老师：" + course.getTeacher() +
                    " || 课程描述：" + course.getDescription()
            );
        }
    }
}
