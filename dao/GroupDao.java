package com.chhnet.dao;

import com.chhnet.vo.Course;
import com.chhnet.vo.Group;
import com.chhnet.vo.Student;
import com.chhnet.vo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupDao extends JpaRepository<Group,Integer> {
    List<Group> findGroupByTeacherListAndCourseList(Teacher teacher, Course course);
    Group findGroupByStudentList(Student student);
    Group findGroupBygId(Integer id);
}
