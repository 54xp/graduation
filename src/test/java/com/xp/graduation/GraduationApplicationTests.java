package com.xp.graduation;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xp.graduation.bean.Level;
import com.xp.graduation.bean.ScoreForm;
import com.xp.graduation.bean.TrainingSchedule;
import com.xp.graduation.bean.User;
import com.xp.graduation.mapper.FeedBackMapper;
import com.xp.graduation.mapper.ScoreFormMapper;
import com.xp.graduation.mapper.TrainingScheduleMapper;
import com.xp.graduation.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GraduationApplicationTests {

    @Autowired
    UserMapper uMapper;
    @Autowired
    TrainingScheduleMapper tMapper;
    @Autowired
    ScoreFormMapper sMapper;
    @Autowired
    FeedBackMapper fMapper;
    @Test
    void contextLoads() {
    }

    @Test
    public void testMybatis(){
        PageHelper.startPage(1,4);
        List<User> users = uMapper.findUserByLevel(2);
        PageInfo<User> pinfo = new PageInfo<>(users);
        System.out.println("pinfo.getPages() = " + pinfo.getPages());
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }  

    @Test
    public void testfindUserByNameandPwd(){
        User user = new User();
        user.setUsername("熊鹏1");
        user.setPassword("123456");
        User user1 = uMapper.findUserByNameAndPwd(user);
        System.out.println("user1 = " + user1);
        System.out.println(null == user1);
    }
    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("熊鹏1");
        user.setPassword("123456");
        user.setPhone("13255556666");
        uMapper.insertUserStu(user);
    }
    @Test
    public void testFindTeas(){
        String teaName = "任";
        System.out.println("teaName = " + teaName);
        List<User> user = uMapper.findTeas("任");
        for (User user1 : user) {
            System.out.println("user1 = " + user1);
        }
    }

    // 测试 lombok
    @Test
    public void testTrainingSchedule(){
        TrainingSchedule t = new TrainingSchedule();
        t.setAddress("11");
        System.out.println("t = " + t);
    }

    @Test
    public void testFindAllNoTeachers(){
        Level stuLevel = fMapper.sumAchievement();
        System.out.println("stuLevel = " + stuLevel);
    }

    // 通过用户名查重
    @Test
    public void testfindUnique(){
        User unique = uMapper.findUnique("黑马2");
        System.out.println("unique = " + unique);
        System.out.println("unique = " + StringUtils.isEmpty(unique));
    }

    // 根据用户名查询实训状态
    @Test
    public void testFindStatus(){
        String status = sMapper.findStatus("熊鹏");
        System.out.println("status = " + status.equals('1'));
    }

    // 测试批量查询参数为数组
    @Test
    public void testFindPname(){
        String []s = {"2","4","5"};
        ArrayList<User> teacher = uMapper.selectTeacher(s);
        for (User user : teacher) {
            System.out.println("user = " + user);
        }
    }


}
