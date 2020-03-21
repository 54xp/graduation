package com.xp.graduation.controller;

import com.xp.graduation.bean.User;
import com.xp.graduation.mapper.UserMapper;
import com.xp.graduation.utils.md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * @author xp
 * @create 2020-01-13 11:46
 */
@Controller
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @PostMapping("/user/login")
    public String login(User user,
                        Map<String,Object> map,
                        HttpSession session){
        user.setPassword(md5.getMD5(user.getPassword()));
        User userinfo = userMapper.findUserByNameAndPwd(user);
        if(!(userinfo == null)){
            session.setAttribute("loginUserInfo",userinfo);
            session.removeAttribute("message");
            // 重定向 防止表单重复提交
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名或密码错误");
            return "login";
        }
    }

    @PostMapping("/user/reg")
    public String register(User user, Map<String,Object> map){
        System.out.println("user = " + user);
        User u = userMapper.findUnique(user.getUsername());
        if(StringUtils.isEmpty(u)){
            user.setPassword(md5.getMD5(user.getPassword()));
            userMapper.insertUserStu(user);
        }else{
           map.put("msg","用户名已存在");
        }

        return "login";
    }

    @GetMapping("/signout")
    public String signout(){
        return "login";
    }

    // 跳转至修改用户信息界面
    @GetMapping("/updateInfo")
    public String updateInfo(){
        return "updateInfo";
    }

    // 修改密码
    @PostMapping("/updatePwd")
    public  String updatePwd(@RequestParam("username") String username,
                             @RequestParam("newPwd") String pwd,
                             @RequestParam("oldPwd") String oldPwd){
        userMapper.updatePwd(username,md5.getMD5(pwd),md5.getMD5(oldPwd));
        return "login";
    }

    // 短信验证码
    @ResponseBody
    @PostMapping("/verCode")
    public String verCode(@RequestParam("phone") String phone){
        System.out.println("phone = " + phone);
  //      String phonecode = PhoneCode.getPhonemsg(phone);
        String phonecode = "123456";
        return phonecode;
    }
}
