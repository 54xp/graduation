package com.xp.graduation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xp.graduation.bean.ExcelData;
import com.xp.graduation.bean.Level;
import com.xp.graduation.bean.TrainingSchedule;
import com.xp.graduation.bean.User;
import com.xp.graduation.mapper.FeedBackMapper;
import com.xp.graduation.mapper.TrainingScheduleMapper;
import com.xp.graduation.mapper.UserMapper;
import com.xp.graduation.utils.ExportExcelUtils;
import com.xp.graduation.utils.md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * @author xp
 * @create 2020-01-18 15:29
 */
@Controller
public class AdminController {
    @Autowired
    TrainingScheduleMapper tMapper;
    @Autowired
    UserMapper uMapper;
    @Autowired
    FeedBackMapper fMapper;

    // 查询所有的企业发布的实训项目
    @GetMapping("/admin")
    public String trainPoj(Model model){
        List<TrainingSchedule> pojs = tMapper.findPojs();
        for (TrainingSchedule poj : pojs) {
            System.out.println("poj = " + poj);
        }
        model.addAttribute("pojs",pojs);
        return "adminTrainPoj";
    }

    // 进入项目详情界面
    @GetMapping("/admin/{id}")
    public String shareTeachers(@PathVariable("id") Integer id,Model model){
        System.out.println("id = " + id);
        TrainingSchedule trainingSchedule = tMapper.getInfoById(id);
        model.addAttribute("trainingSchedule",trainingSchedule);
        return "adminTrainingScheduleInfo";
    }

    // 修改项目详情
    @PostMapping("/admin/{id}")
    public String updateTrainingScheduleById(@PathVariable("id") Integer id,
                                             TrainingSchedule trainingSchedule,
                                             Model model){
        System.out.println("trainingSchedule = " + trainingSchedule);
        tMapper.updateTrainingScheduleInfo(trainingSchedule);
        List<TrainingSchedule> pojs = tMapper.findPojs();
        model.addAttribute("pojs",pojs);
        return "adminTrainPoj";
    }

    // 跳转至管理员添加用户界面
    @GetMapping("adminInsert")
    public String adminInsert(){
        return "adminInsert";
    }

    // 管理员添加教师或企业角色
    @PostMapping("adminInsertUser")
    public  String adminInsertUser(User user,HttpSession session){
        System.out.println("user = " + user);
        User u = uMapper.findUnique(user.getUsername());
        if(StringUtils.isEmpty(u)){
            user.setPassword(md5.getMD5(user.getPassword()));
            uMapper.adminInsertUser(user);
        }else{
            session.setAttribute("message","用户名已存在！！！");
        }
        return "redirect:/main.html";
    }


    // 导出教师信息
    @PostMapping("exports")
    @ResponseBody
    public void exports(String[] check_val,HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("Arrays.asList(check_val).toString() = " + Arrays.asList(check_val));

        //获取数据
        ArrayList<User> teachers = uMapper.selectTeacher(check_val);
        ExcelData data = new ExcelData();
        data.setName("教师信息表");
        List<String> titles = new ArrayList();
        titles.add("教师ID");
        titles.add("教师名");
        titles.add("联系方式");
        titles.add("性别");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for (int i = 0; i < teachers.size(); i++) {
          List<Object> row = new ArrayList();
                row.add(teachers.get(i).getId());
                row.add(teachers.get(i).getUsername());
                row.add(teachers.get(i).getPhone());
                if("0".equals(teachers.get(i).getGender())){
                    row.add("女");
                }else{
                    row.add("男");
                }
                rows.add(row);
        }

        data.setRows(rows);


        //生成本地
     /*   File f = new File("c:/test.xlsx");
        FileOutputStream out = new FileOutputStream(f);
        ExportExcelUtils.exportExcel(data, out);
        out.close();*/

        String name = UUID.randomUUID().toString().replace("-","")+ "——教师信息表.xlsx";
        ExportExcelUtils.exportExcel(response,name,data);
        System.out.println("导出完成！！！");
    }

    // 报表
    @GetMapping("table")
    public String echarts4(HttpSession session){
        Level levels = fMapper.sumLevel();
        session.setAttribute("level",levels);
        Level stuLevel = fMapper.sumAchievement();
        session.setAttribute("stuLevel",stuLevel);
        return "adminInfoTable";
    }

    //进入教师信息界面
    @GetMapping("/adminUpdateTea/{id}")
    public String adminUpdateTea(@PathVariable("id") Integer id, Model model){
            PageHelper.startPage(1,4);
            List<User> users = uMapper.findUserByLevel(id);
            model.addAttribute("users",users);
            PageInfo<User> pinfo = new PageInfo<>(users);
            model.addAttribute("pages",pinfo.getPages());  // 页码数量
            return "adminUpdateTeachers";
    }

    // 根据id 回显教师信息
    @ResponseBody
    @PostMapping("/adminFindTeacher")
    public User tea(int id){
        System.out.println("id = " + id);
        User teacher = uMapper.finTeacherById(id);
        return teacher;
    }

    // 修改教师信息
    @PostMapping("adminUpdateTeaInfo")
    public String adminUpdateTeaInfo(User teacher,Model model){
        System.out.println("teacher = " + teacher);
        uMapper.updateTeacherInfo(teacher);
        PageHelper.startPage(1,4);
        List<User> users = uMapper.findUserByLevel(2);
        model.addAttribute("users",users);
        return "adminUpdateTeachers";
    }

    // 删除教师信息
    @PostMapping("deleteTeaInfo")
    @ResponseBody
    public String deleteTeaInfo(int id){
        uMapper.deleteTeaInfo(id);
        return "success";
    }

    // 实训费用显示
    @GetMapping("adminCost")
    public  String adminCost(Model model){
        List<TrainingSchedule> list = tMapper.findPojs();
        model.addAttribute("pojs",list);
        return "adminCostPage";
    }

    // 实训费用修改
    @PostMapping("adminUpdateCostInfo")
    public String adminUpdateTrainingScheduleInfo(TrainingSchedule trainingSchedule,Model model){
        tMapper.updateTrainInfo(trainingSchedule);
        List<TrainingSchedule> list = tMapper.findPojs();
        model.addAttribute("pojs",list);
        return "adminCostPage";
    }

    // 实训地址展示
    @GetMapping("adminAddress")
    public  String adminAddress(Model model){
        List<TrainingSchedule> list = tMapper.findPojs();
        model.addAttribute("pojs",list);
        return "adminAddressPage";
    }

    // 实训费用修改
    @PostMapping("adminUpdateAddressInfo")
    public String adminUpdateAddressInfo(TrainingSchedule trainingSchedule,Model model){
        tMapper.updateTrainInfo(trainingSchedule);
        List<TrainingSchedule> list = tMapper.findPojs();
        model.addAttribute("pojs",list);
        return "adminAddressPage";
    }
}
