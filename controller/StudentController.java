package com.chhnet.controller;

import com.chhnet.service.StudentService;
import com.chhnet.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

@Controller
public class StudentController extends HttpServlet {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ServletContext servletContext;
    @PostMapping("/signin")
    public String signin(Student student, String code, Model model){
        int status = studentService.signin(student,code,servletContext);
        if(status==1){
            return "success";
        }else {
            model.addAttribute("msg","签到码错误！");
        }
        return "signin";
    }
    @RequestMapping("/signin/{id}")
    public String toSignin(@PathVariable("id")Integer studentId, Model model){
        if(studentService.toSignin(studentId,servletContext)){
            model.addAttribute("studentId",studentId);
            return "signin";
        }
        model.addAttribute("msg","当前没有签到任务");
        return "signin";
    }

}

