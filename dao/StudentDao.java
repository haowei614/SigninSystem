package com.chhnet.dao;

import com.chhnet.vo.Group;
import com.chhnet.vo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDao extends JpaRepository<Student,Integer> {
    List<Student> findStudentsByGroup(Group group);
    Student findStudentByAccount(String account);
    Student findStudentByStudentId(Integer id);
}
