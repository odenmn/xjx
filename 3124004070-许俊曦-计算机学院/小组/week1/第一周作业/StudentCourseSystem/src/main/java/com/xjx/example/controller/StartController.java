package com.xjx.example.controller;

import com.xjx.example.entity.Student;
import com.xjx.example.entity.User;
import com.xjx.example.service.StudentService;

import javax.jws.Oneway;
import java.util.Scanner;

public class StartController {
    private RegisterAndLoginController registerAndLoginController = new RegisterAndLoginController();
    Scanner sc = new Scanner(System.in);
    private StudentController studentController = new StudentController();
    private AdminController adminController = new AdminController();

    public void start(){
        while (true) {
            System.out.println("=================");
            System.out.println("学生选课管理系统");
            System.out.println("=================");
            System.out.println("1. 登录");
            System.out.println("2. 注册");
            System.out.println("3. 退出");
            System.out.println("请选择操作（输入 1-3）：");
            String command = sc.nextLine();
            switch (command) {
                case "1":
                    User user = new RegisterAndLoginController().Login();
                    if (user == null){
                        break;
                    }
                    if (user.getRole().equals("学生")) {
                        //身份为学生
                        StudentService studentService = new StudentService();
                        Student student = studentService.getstudentByName(user.getUsername());
                        studentController.showStudentMenu(student);
                    }else {
                        //身份为管理员
                        adminController.showAdminMenu();
                    }
                    break;
                case "2":
                    //注册用户
                    registerAndLoginController.register();
                    break;
                case "3":
                    //退出系统
                    System.out.println("退出系统成功！");
                    return;
                default:
                    System.out.println("请输入正确的命令！");
            }
        }
    }




}
