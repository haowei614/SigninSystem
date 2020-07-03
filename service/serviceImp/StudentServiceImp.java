package com.chhnet.service.serviceImp;

import com.chhnet.common.SigninStatus;
import com.chhnet.dao.GroupDao;
import com.chhnet.dao.SigninRecordDao;
import com.chhnet.dao.StudentDao;
import com.chhnet.service.StudentService;
import com.chhnet.vo.Group;
import com.chhnet.vo.SigninRecord;
import com.chhnet.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private SigninRecordDao signinRecordDao;
    @Autowired
    private StudentDao studentDao;

    @Override
    public int signin(Student student, String code, ServletContext servletContext) {
        System.out.println(student);
        Group group = groupDao.findGroupByStudentList(student);//查询应签到的班级
        //从servletContext获取应该签到的班级taskList
        List<SigninStatus> taskList = (List<SigninStatus>) servletContext.getAttribute("taskList");
        for (SigninStatus signinStatus : taskList) {
            if (signinStatus.getTaskId() == group.getgId()) {
                if (code.equals(signinStatus.getCode())) { //判断签到码是否正确
                    SigninRecord signinRecord = new SigninRecord();  //生成签到对象
                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    signinRecord.setDate(date);
                    signinRecord.setStudentId(student.getStudentId());
                    signinRecord.setGroupId(group.getgId());
                    long time = System.currentTimeMillis();
                    if (time - signinStatus.getStartTime() <= 30 * 60 * 1000) {
                        signinRecord.setStatus("正常");
                    } else {
                        signinRecord.setStatus("迟到");
                    }
                    signinRecordDao.save(signinRecord);
                    List<Student> studentList = signinStatus.getStudentList();
                    //从该签到的学生中把请假的移除
                    for (int i = 0; i < studentList.size(); i++) {
                        if (student.getStudentId() == studentList.get(i).getStudentId()) {
                            studentList.remove(i);
                        }
                    }
                    //再把任务列表放回到域对象
                    servletContext.setAttribute("taskList", taskList);
                    return 1;
                }
            }
        }
        return -1;
    }

    @Override
    public Student login(String account, String pwd) {
        Student student = studentDao.findStudentByAccount(account);
        if (student != null && student.getPwd().equals(pwd)) {
            return student;
        }
        return null;
    }

    @Override
    public Boolean toSignin(Integer studentId, ServletContext servletContext) {
        boolean result = false;
        Group group = groupDao.findGroupByStudentList(studentDao.findStudentByStudentId(studentId));
        List<SigninStatus> taskList = (List<SigninStatus>) servletContext.getAttribute("taskList");
        if (taskList != null) {
            for (SigninStatus s : taskList) {
                if (s.getTaskId() == group.getgId()) {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public Student findStudentById(Integer id) {
        return studentDao.findStudentByStudentId(id);
    }

}