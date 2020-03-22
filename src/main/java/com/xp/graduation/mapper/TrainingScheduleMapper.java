package com.xp.graduation.mapper;

import com.xp.graduation.bean.TrainingSchedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xp
 * @create 2020-01-18 9:42
 */
@Mapper
public interface TrainingScheduleMapper {
    // 企业发布信息到实训计划表
    void insertTrainingSchedule(TrainingSchedule trainingSchedule);

    // 添加上传文件名到实训计划表
    void updateDocToTrainingSchedule(String doc);

    // 查询所有的实训项目
    List<TrainingSchedule> findPojs();

    // 查询所有审批通过的实训项目
    List<TrainingSchedule> findPojsPattr1();

    // 查看项目详情
    TrainingSchedule getInfoById(Integer id);

    // 修改项目详情
    void updateTrainingScheduleInfo(TrainingSchedule trainingSchedule);

    // 费用修改与地址修改
    void updateTrainInfo(TrainingSchedule trainingSchedule);

    //  根据公司名查询其发布的项目
    List<TrainingSchedule> selectComPoj(String companyName);

    // 修改项目状态
    void updateTrainPattr1(Integer pojid);
}
