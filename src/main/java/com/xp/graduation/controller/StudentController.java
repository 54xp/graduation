package com.xp.graduation.controller;

import com.xp.graduation.bean.FeedBack;
import com.xp.graduation.bean.ScoreForm;
import com.xp.graduation.bean.TrainingSchedule;
import com.xp.graduation.bean.User;
import com.xp.graduation.mapper.*;
import com.xp.graduation.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author XP
 * @create 2020-02-23 21:58
 */
@Controller
@Transactional
public class StudentController {
    @Autowired
    TrainingScheduleMapper tMapper;
    @Autowired
    UserMapper uMapper;
    @Autowired
    ApplicationFormMapper aMapper;
    @Autowired
    ScoreFormMapper sMapper;
    @Autowired
    FeedBackMapper fMapper;

    // 跳转至实训报名界面
    @GetMapping("/signin")
    public String signup(Model model){
        List<TrainingSchedule> pojs = tMapper.findPojsPattr1();
        model.addAttribute("pojs",pojs);
        return "studentSignup";
    }

    // 进入项目详情界面 选择指导老师
    @RequestMapping("/student/{id}")
    public String shareTeachers(@PathVariable("id") Integer id, Model model){
        System.out.println("id = " + id);
        TrainingSchedule trainingSchedule = tMapper.getInfoById(id);
        model.addAttribute("trainingSchedule",trainingSchedule);
        model.addAttribute("teachers",uMapper.findUserByLevel(2));
        return "studentbaoming";
    }

    // 报名
    @PostMapping("/student/{id}")
    public String updateTrainingScheduleById(@PathVariable("id") Integer id,
                                             @RequestParam("teacher") String teacher,
                                             @RequestParam("username") String username,
                                             TrainingSchedule trainingSchedule,
                                             HttpSession session){
        String s = sMapper.findStatus(username);
        String flag = "0";
        if(flag.equals(s)){
            session.setAttribute("message","当前有未完成的实训,请勿重复报名");
            return "redirect:/main.html";
        }

        aMapper.insertInfo(username,teacher,trainingSchedule);
        sMapper.insertScoreForm(username,teacher,trainingSchedule);

        return "redirect:/main.html";
    }

    // 跳转至实习报告上传界面
    @GetMapping("stuupload")
    public String stuupload(){
        return "studentUploadForm";
    }

    // 实习报告上传
    @PostMapping("stuUploadDoc")
    public String stuUploadDoc(@RequestParam("upload") MultipartFile upload,
                               @RequestParam("username") String username,
                               HttpSession session)
            throws IOException {
        String filename = UploadFileUtil.uploadFile(upload);
        sMapper.updateDoc(filename,username);
        session.setAttribute("message","实习报告上传成功！！！");

        return "redirect:/main.html";
    }

    // 跳转至学生成绩查询界面
    @GetMapping("/stuachievement")
    public String stuachievement(HttpSession session,Model model){
        User loginUserInfo = (User) session.getAttribute("loginUserInfo");
        List<ScoreForm> scoreList = sMapper.selectScore(loginUserInfo.getUsername());
        model.addAttribute("scoreList",scoreList);
        return "studentScoreList";
    }

    // 跳转至实训效果反馈界面
    @GetMapping("/stuFeed")
    public String feedback(HttpSession session,Model model){
        User loginUserInfo = (User) session.getAttribute("loginUserInfo");
        String pname = sMapper.selectPname(loginUserInfo.getUsername());
        model.addAttribute("pname",pname);
        return "studentFeedback";
    }

    // 提交实训效果反馈
    @PostMapping("stuFeedback")
    public String stuFeedback(FeedBack feedBack,Model model,HttpSession session){
        System.out.println("feedBack = " + feedBack);
        fMapper.insertFeedBack(feedBack);
        sMapper.updateStatus(feedBack.getUsername());
        session.setAttribute("message","实训反馈提交成功！！！");
        return "redirect:/main.html";
    }
}
