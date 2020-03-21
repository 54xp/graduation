package com.xp.graduation.controller;

import com.xp.graduation.bean.ScoreForm;
import com.xp.graduation.bean.TrainingSchedule;
import com.xp.graduation.bean.User;
import com.xp.graduation.mapper.ScoreFormMapper;
import com.xp.graduation.mapper.TrainingScheduleMapper;
import com.xp.graduation.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * @author xp
 * @create 2020-01-17 17:19
 */
@Controller
public class CompanyController {
    @Autowired
    TrainingScheduleMapper trainingScheduleMapper;
    @Autowired
    ScoreFormMapper sMapper;

    @RequestMapping("/company")
    public String publish(){
        return "companyPublish";
    }

    @PostMapping("/insertTrainingSchedule")
    public String insertTrainingSchedule(TrainingSchedule trainingSchedule,HttpServletRequest request,
                                         @RequestParam("upload") MultipartFile upload)
            throws IOException {
        System.out.println("trainingSchedule = " + trainingSchedule);

        String filename = UploadFileUtil.uploadFile(upload);

        trainingScheduleMapper.insertTrainingSchedule(trainingSchedule);
        trainingScheduleMapper.updateDocToTrainingSchedule(filename);
        return "redirect:/main.html";
    }

    // 跳转至企业评价学生成绩界面
    @RequestMapping("/companyform")
    public String comform(HttpSession session, Model model){
        User loginUserInfo = (User) session.getAttribute("loginUserInfo");
        List<ScoreForm> stuScores = sMapper.comSelectStuScorce(loginUserInfo.getUsername());
        model.addAttribute("comScoreList",stuScores);
        return "companyStuScore";
    }

    // 企业评价学生成绩
    @PostMapping("comStuPj")
    public String comStuPj(@RequestParam("stuname") String stuname,
                           @RequestParam("sturesult") String sturesult,
                           HttpSession session,Model model){
        sMapper.comUpdateStuScore(stuname,sturesult);
        //    String companyStatus = sMapper.selectCompanyStatus(stuname);
        User loginUserInfo = (User) session.getAttribute("loginUserInfo");
        List<ScoreForm> stuScores = sMapper.comSelectStuScorce(loginUserInfo.getUsername());
        model.addAttribute("comScoreList",stuScores);
        return "companyStuScore";
    }

}
