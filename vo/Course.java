package com.chhnet.vo;

import javax.persistence.*;
import java.util.List;

@Entity(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cId;
    @Column(name = "cname", length = 100, nullable = false)
    private String cname;
    @ManyToMany
    private List<Teacher> teacherList;
    @ManyToMany(mappedBy = "courseList")
    private List<Group> groupList;

    public List<Group> getGroupList() {
        return groupList;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cId=" + cId +
                ", cname='" + cname + '\'' +
                ", teacherList=" + teacherList +
                ", groupList=" + groupList +
                '}';
    }
}