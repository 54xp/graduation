package com.xp.graduation.mapper;

import com.xp.graduation.bean.TrainingSchedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author XP
 * @create 2020-02-24 23:49
 */
@Mapper
public interface ApplicationFormMapper {
    // 报名 插入信息到报名表
    void insertInfo(@Param("username") String username, @Param("teacher") String teacher, @Param("trainingSchedule") TrainingSchedule trainingSchedule);
}
