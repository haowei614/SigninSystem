package com.chhnet.vo;

import javax.persistence.*;
import java.util.List;

@Entity(name = "class")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gId;
    @Column(length = 20, nullable = false)
    private String name;
    @ManyToMany()
    private List<Course> courseList;
    @ManyToMany()
    private List<Teacher> teacherList;
    @OneToMany(mappedBy = "group")
    private List<Student> studentList;

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Group{" +
                "gId=" + gId +
                ", name='" + name + '\'' +
                '}';
    }
}