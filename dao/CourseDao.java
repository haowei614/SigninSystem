package com.chhnet.dao;

import com.chhnet.vo.Course;
import com.chhnet.vo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseDao extends JpaRepository<Course, Integer> {
    List<Course> findCourseByTeacherList(Teacher teacher);
}