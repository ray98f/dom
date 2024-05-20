package com.wzmtr.dom.utils.task;

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


    @Scheduled(cron = "0 1 5 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncData() {

    }


}
