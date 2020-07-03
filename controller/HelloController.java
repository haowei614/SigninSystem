package com.chhnet.controller;

import com.chhnet.service.StudentService;
import com.chhnet.service.TeacherService;
import com.chhnet.vo.Student;
import com.chhnet.vo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String account, String pwd, Model model) {
        Teacher teacher = teacherService.login(account, pwd);
        if (teacher != null) {
            model.addAttribute("teacher", teacher);
            return "teacher";
        } else {
            Student student = studentService.login(account, pwd);
            if (student != null) {
                model.addAttribute("student", student);
                return "student";
            } else {
                model.addAttribute("msg", "用户名或密码错误！");
                return "login";
            }
        }
    }
}
