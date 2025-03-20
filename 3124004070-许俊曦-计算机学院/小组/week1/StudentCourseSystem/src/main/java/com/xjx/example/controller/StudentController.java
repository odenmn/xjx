package com.xjx.example.controller;

import com.xjx.example.entity.Course;
import com.xjx.example.entity.Student;
import com.xjx.example.entity.StudentCourseSelection;
import com.xjx.example.service.CourseService;
import com.xjx.example.service.StudentCourseSelectionService;
import com.xjx.example.service.StudentService;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StudentController {
    private StudentService studentService = new StudentService();
    private CourseService courseService = new CourseService();
    private StudentCourseSelectionService studentCourseSelectionService = new StudentCourseSelectionService();
    Scanner sc = new Scanner(System.in);

    public void showStudentMenu(Student student) {
        while (true) {
            System.out.println("===== 学生菜单 =====");
            System.out.println("1. 查看可选课程");
            System.out.println("2. 选择课程");
            System.out.println("3. 退选课程");
            System.out.println("4. 查看已选课程");
            System.out.println("5. 修改手机号");
            System.out.println("6. 退出");
            System.out.print("请选择操作（输入 1 - 6）：");
            String command = sc.nextLine();
            switch (command) {
                case "1":
                    viewAvailableCourses();
                    break;
                case "2":
                    selectCourse(student.getStudentId());
                    break;
                case "3":
                    deleteAlreadySelectCourse(student.getStudentId());
                    break;
                case "4":
                    viewSelectedCourses(student.getStudentId());
                    break;
                case "5":
                    updatePhone(student.getStudentId());
                    break;
                case "6":
                    System.out.println("退出学生菜单成功");
                    return;
                default:
                    System.out.println("无效的选择，请重新输入");
            }
        }
    }

    //查看可选课程
    private void viewAvailableCourses() {
        List<Course> availbleCourses = courseService.getAllCourses();
        System.out.println("======可选课程列表=====");
        for (Course course:availbleCourses) {
            System.out.println("课程ID：" + course.getCourseId() + " || 课程名称：" + course.getCourseName() +
                            " || 课程学分：" + course.getCredit() + " || 授课老师：" + course.getTeacher() +
                            " || 课程描述：" + course.getDescription()
                    );
        }
    }

    //选择课程
    private void selectCourse(int studentId) {
        if (courseService.getAllCourses().isEmpty()){
            System.out.println("当前没有可以选择的课程，请稍后再来选课~~");
            return;
        }
        viewAvailableCourses();
        System.out.print("请输入要选择的课程 ID：");
        int courseId = sc.nextInt();
        sc.nextLine();
        List<StudentCourseSelection> selectedCourses = studentCourseSelectionService.getCoursesByStudentId(studentId);
        if (selectedCourses.size() >= 5) {
            System.out.println("你已选满 5 门课程，不能再选。");
            return;
        }
        studentCourseSelectionService.addStudentCourse(studentId, courseId);
        System.out.println("选课成功");
    }

    private void viewSelectedCourses(int studentId){
        //获取已选课程的Id
        List<StudentCourseSelection> selectedCourses = studentCourseSelectionService.getCoursesByStudentId(studentId);
        System.out.println("你已选的课程列表：");
        //通过课程Id打印出已选课程的信息
        for (StudentCourseSelection scs : selectedCourses) {
            Course course = courseService.getCoursesById(scs.getCourseId());
            if (course != null) {
                System.out.println("课程ID：" + course.getCourseId() + " || 课程名称：" + course.getCourseName() +
                        " || 课程学分：" + course.getCredit() + " || 授课老师：" + course.getTeacher() +
                        " || 课程描述：" + course.getDescription()
                );
            }
        }
    }
    private void deleteAlreadySelectCourse(int studentId){
        if (studentCourseSelectionService.getCoursesByStudentId(studentId).isEmpty()){
            System.out.println("当前您还未添加选课，请先去选课吧~~");
            return;
        }
        viewSelectedCourses(studentId);
        System.out.print("请输入要退选的课程 ID：");
        int courseId = sc.nextInt();
        sc.nextLine();
        studentCourseSelectionService.deleteStudentCourse(studentId, courseId);
        System.out.println("退课成功~~");
    }

    private void updatePhone(int studentId) {
        System.out.print("请输入新的手机号：");
        String phone = sc.nextLine();
        studentService.updateStudentPhone(studentId, phone);
        System.out.println("手机号修改成功~~");
    }

}


