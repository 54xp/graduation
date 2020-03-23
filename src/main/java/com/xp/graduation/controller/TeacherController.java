package com.xp.graduation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xp.graduation.bean.ScoreForm;
import com.xp.graduation.bean.TrainingSchedule;
import com.xp.graduation.bean.User;
import com.xp.graduation.mapper.ScoreFormMapper;
import com.xp.graduation.mapper.TrainingScheduleMapper;
import com.xp.graduation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

import static com.xp.graduation.utils.DownFileUtil.downFile;

/**
 * @author xp
 * @create 2020-01-16 14:17
 */
@Controller
public class TeacherController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ScoreFormMapper sMapper;

    // 初始化教师信息查询(学生)
    @GetMapping("/teacher/{id}")
    public String getTeachersInfo(@PathVariable("id") Integer id, Model model,HttpSession session){
        System.out.println("初始化教师信息id = " + id);
        PageHelper.startPage(1,4);
        List<User> users = userMapper.findUserByLevel(id);
        model.addAttribute("users",users);
        PageInfo<User> pinfo = new PageInfo<>(users);
        model.addAttribute("pages",pinfo.getPages());  // 页码数量
        return "teacherInfoList";
    }

    // 分页教师信息查询
    @PostMapping("/page")
    @ResponseBody
    public List<User> getTeachersInfoPage(@RequestParam("pagenum") String pagenum, Model model){
        System.out.println("pagenum = " + pagenum);
        PageHelper.startPage(Integer.parseInt(pagenum),4);
        List<User> users = userMapper.findUserByLevel(2);
        for (User user : users) {
            System.out.println("user = " + user);
            if("0".equals(user.getGender())){
                user.setGender("女");
            }else{
                user.setGender("男");
            }
        }
        return users;
    }

    // 根据姓名查询教师信息
    @PostMapping("/teacher")
    public String getTeachersInfo(@RequestParam("teaName") String teaName, Model model){
        System.out.println("teaName = " + teaName);
        List<User> users = userMapper.findTeas(teaName.trim());
        model.addAttribute("users",users);
        return "teacherInfoList";
    }

    // 跳转至教师评价学生成绩界面
    @GetMapping("/teaform")
    public String teaform(HttpSession session,Model model){
        User loginUserInfo = (User) session.getAttribute("loginUserInfo");
        List<ScoreForm> stuScores = sMapper.selectStuScorce(loginUserInfo.getUsername());
        model.addAttribute("teaScoreList",stuScores);
        return "teacherStuScore";
    }

    // 教师评价学生成绩
    @PostMapping("teaStuPj")
    public String teaStuPj(@RequestParam("stuname") String stuname,
                           @RequestParam("sturesult") String sturesult,
                           HttpSession session,Model model){
        sMapper.updateStuScore(stuname,sturesult);
    //    String companyStatus = sMapper.selectCompanyStatus(stuname);
        User loginUserInfo = (User) session.getAttribute("loginUserInfo");
        List<ScoreForm> stuScores = sMapper.selectStuScorce(loginUserInfo.getUsername());
        model.addAttribute("teaScoreList",stuScores);
        return "teacherStuScore";
    }

    @GetMapping("/teacherDown")
    public String teacherDown(HttpSession session,Model model){
        User loginUserInfo = (User) session.getAttribute("loginUserInfo");
        List<ScoreForm> list = sMapper.selectStuDoc(loginUserInfo.getUsername());
        for (int i = 0; i < list.size(); i++) {
            String reportdoc = list.get(i).getReportdoc();
            String[] s = reportdoc.split("_");
            if(s.length > 1){
                list.get(i).setShowreportdoc(s[1]);
            }else{
                list.get(i).setShowreportdoc("暂未提交实习报告");
            }
        }
        model.addAttribute("docs",list);
        return "teacherStuDoc";
    }

    // 下载实习报告
    @GetMapping("/download/{filename}")
    @ResponseBody
    public void downLoad(   HttpServletResponse response,
                           @PathVariable("filename") String filename)
            throws Exception {
        downFile(response,filename);
    }
}
