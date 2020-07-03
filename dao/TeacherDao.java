package com.chhnet.dao;

import com.chhnet.vo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDao extends JpaRepository<Teacher,Integer> {
    Teacher findTeacherByTeacherId(Integer id);
    Teacher findTeacherByAccount(String account);
}
