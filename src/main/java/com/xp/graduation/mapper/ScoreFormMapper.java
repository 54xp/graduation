package com.xp.graduation.mapper;

import com.xp.graduation.bean.ScoreForm;
import com.xp.graduation.bean.TrainingSchedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author XP
 * @create 2020-02-24 23:49
 */
@Mapper
public interface ScoreFormMapper {
    // 插入信息到报名表
    void insertScoreForm(@Param("username") String username, @Param("teacher") String teacher, @Param("trainingSchedule") TrainingSchedule trainingSchedule);

    // 根据学生名返回实训状态
    String findStatus(String username);

    // 添加实习报告到实训成绩表
    void updateDoc(@Param("filename") String filename,@Param("username") String username);

    // 根据学生名查询未完成的项目名
    String findPname(String username);

    // 根据学生名查询所有实训项目成绩
    List<ScoreForm> selectScore(String username);

    // 查询教师名下的所有学生成绩
    List<ScoreForm> selectStuScorce(String teacher);

    // 插入教师评价的成绩
    void updateStuScore(@Param("stuname") String stuname, @Param("sturesult") String sturesult);

    // 查询企业名下的所有学生成绩
    List<ScoreForm> comSelectStuScorce(String companyname);

    // 插入企业评价的成绩
    void comUpdateStuScore(@Param("stuname") String stuname, @Param("sturesult") String sturesult);

    // 实训反馈，根据学生名查询未结束的项目名
    String selectPname(String username);

    // 学生反馈后修改项目实训状态
    void updateStatus(String username);

    // 查询教师名下的学生实习信息
    List<ScoreForm> selectStuDoc(String teacherName);

    // 查询该学生企业实训成绩状态
//    String selectCompanyStatus(String stuname);
}
