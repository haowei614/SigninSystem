package com.chhnet.common;

import com.chhnet.dao.GroupDao;
import com.chhnet.dao.SigninRecordDao;
import com.chhnet.dao.StudentDao;
import com.chhnet.vo.*;

import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SigninStatus {
    private Integer taskId;
    private Teacher startId;
    private long startTime;
    private Course course;
    private String code;
    private List<Student> studentList ;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getStartId() {
        return startId;
    }

    public void setStartId(Teacher startId) {
        this.startId = startId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }


    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
    public String startSignin(Teacher teacher, Course course, String group, ServletContext servletContext, GroupDao groupDao
            , StudentDao studentDao, SigninRecordDao signinRecordDao){
        List<SigninStatus> taskList = (List<SigninStatus>) servletContext.getAttribute("taskList");
        if(taskList==null){
            taskList = new ArrayList<>(1);
        }
        String[] groups = group.split(",");
        List<Group> groupList = groupDao.findGroupByTeacherListAndCourseList(teacher,course);
        Random random = new Random();
        String code = "";
        for(String gro:groups) {
                    SigninStatus signinStatus = new SigninStatus();
                    signinStatus.setTaskId(groupDao.findGroupBygId(Integer.parseInt(gro)).getgId());
                    signinStatus.setCourse(course);
                    signinStatus.setStartId(teacher);
                    if(code.equals("")) {
                        for (int i = 0; i < 4; i++) {
                            code += random.nextInt(10);
                        }
                    }
                    signinStatus.setCode(code);
                    signinStatus.setStartTime(System.currentTimeMillis());
                    List<Student> students = studentDao.findStudentsByGroup(groupDao.findGroupBygId(Integer.parseInt(gro)));
                    for(int i = 0;i<students.size();i++){
                        if(students.get(i).getStatus().equals("请假")){
                            System.out.println(students.get(i).getStatus());
                            SigninRecord signinRecord = new SigninRecord();
                            signinRecord.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            signinRecord.setStudentId(students.get(i).getStudentId());
                            signinRecord.setGroupId(groupDao.findGroupBygId(Integer.parseInt(gro)).getgId());
                            signinRecord.setStatus("请假");
                            signinRecordDao.save(signinRecord);
                            students.remove(i);
                        }
                    }
                    signinStatus.setStudentList(students);
                    taskList.add(signinStatus);
                    servletContext.setAttribute("taskList",taskList);
        }
        return code;
    }
}