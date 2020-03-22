package com.xp.graduation.mapper;

import com.xp.graduation.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xp
 * @create 2020-01-16 11:21
 */
@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();

    // 根据用户名 密码 返回用户信息
    @Select("select * from user where username=#{username} and password = #{password}")
    User findUserByNameAndPwd(User user);

    // 注册
    void insertUserStu(User user);

    // 查询不同等级人员信息
    @Select("select * from user where powerlevel = #{id}")
    List<User> findUserByLevel(Integer id);

    // 根据姓名查询教师信息
    @Select("select * from user where powerlevel = 2 and username like concat('%',#{name},'%') ")
    List<User> findTeas(String name);

    // 根据姓名查询企业信息
    @Select("select * from user where powerlevel = 1 and username like concat('%',#{name},'%') ")
    List<User> findComs(String name);

    // 修改用户密码
    void updatePwd(@Param("username") String username, @Param("pwd") String pwd,@Param("oldPwd") String oldPwd);

    // admin 添加企业或教师角色
    void adminInsertUser(User user);

    // 通过用户名查重
    User findUnique(String username);

    // 根据id查询用户信息
    @Select("select * from user where id = #{id}")
    User finTeacherById(int id);

    // 修改教师信息
    void updateTeacherInfo(User teacher);

    // 删除用户信息
    @Delete("delete from user where id = #{id}")
    void deleteTeaInfo(int id);

    // 批量查询教师信息
    ArrayList<User> selectTeacher(String[] ids);
}
