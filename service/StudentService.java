package com.chhnet.service;

import com.chhnet.vo.Student;

import javax.servlet.ServletContext;

public interface StudentService {
    public int signin(Student student, String code, ServletContext servletContext);
    public Student login(String account, String pwd);
    public Boolean toSignin(Integer studentId,ServletContext servletContext);
    public Student findStudentById(Integer id);
}
