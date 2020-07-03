package com.chhnet.controller;

import com.chhnet.service.TeacherService;
import com.chhnet.vo.Course;
import com.chhnet.vo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;

@Controller
public class TeacherController{
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ServletContext servletContext;
    @PostMapping("/startSignin")
    @ResponseBody
    public String startSignin(Teacher teacher, Course course, String group) {
        String code = teacherService.startSignin(teacher,course,group,servletContext);
        return code;
    }

}
