package com.wzmtr.dom.utils.task;

import cn.hutool.core.date.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *  日报数据更新
 * @author  Ray
 * @version 1.0
 * @date 2023/11/20
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DailyDataModifyTask {

    // TODO
    // 每日凌晨5点 更新一些需要同步其他系统数据的日报数据
    // 更新前一天的日报数据
    // 若是周一，更新上周一到周日的周报数据
    // 若是月初第一天，更新上一个月的月初到月末的月报数据

    /**
     * 车辆部每日数据新增
     * */
    @Scheduled(cron = "0 1 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void vehicleAutoCreateTask() {
        String today = DateUtil.today();

    }

    /**
     * 客运部每日数据新增
     * */
    @Scheduled(cron = "0 10 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void trafficAutoCreateTask() {
        String today = DateUtil.today();

    }

    /**
     * 运营日报每日数据新增
     * */
    @Scheduled(cron = "0 20 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void operateAutoCreateTask() {
        String today = DateUtil.today();

    }

    /**
     * 车辆部每日数据同步更新
     * */
    @Scheduled(cron = "0 1 5 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void vehicleSyncData() {

    }

    /**
     * 客运部每日数据同步更新
     * */
    @Scheduled(cron = "0 10 5 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void trafficSyncData() {

    }

    /**
     * 运营日报每日数据同步更新
     * */
    @Scheduled(cron = "0 20 5 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void operateSyncData() {

    }
}
