package com.chhnet.service.serviceImp;

import com.chhnet.common.SigninStatus;
import com.chhnet.dao.GroupDao;
import com.chhnet.dao.SigninRecordDao;
import com.chhnet.dao.StudentDao;
import com.chhnet.dao.TeacherDao;
import com.chhnet.service.TeacherService;
import com.chhnet.vo.Course;
import com.chhnet.vo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;

@Service
public class TeacherServiceImp implements TeacherService {
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private SigninRecordDao signinRecordDao;
    @Override
    public String startSignin(Teacher teacher, Course course, String group, ServletContext servletContext) {
        return new SigninStatus().startSignin(teacher,course,group,servletContext,groupDao,studentDao,signinRecordDao);
    }

    @Override
    public Teacher login(String account, String pwd) {
        Teacher teacher = teacherDao.findTeacherByAccount(account);
        if(teacher!=null&&teacher.getPwd().equals(pwd)){
            return teacher;
        }
        return null;
    }
}
