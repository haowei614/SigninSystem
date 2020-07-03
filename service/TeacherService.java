package com.chhnet.service;

import com.chhnet.vo.Course;
import com.chhnet.vo.Teacher;

import javax.servlet.ServletContext;

public interface TeacherService {
    public String startSignin(Teacher teacher, Course course, String group, ServletContext servletContext);
    public Teacher login(String account,String pwd);
}