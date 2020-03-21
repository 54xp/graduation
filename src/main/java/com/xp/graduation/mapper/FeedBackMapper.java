package com.xp.graduation.mapper;

import com.xp.graduation.bean.FeedBack;
import com.xp.graduation.bean.Level;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xp
 * @create 2020-03-02 11:38
 */
@Mapper
public interface FeedBackMapper {
    // 插入实训反馈信息到实训表
    void insertFeedBack(FeedBack feedBack);

    // 报表统计学生反馈信息
    Level sumLevel();

    // 报表统计学生成绩
    Level sumAchievement();
}
