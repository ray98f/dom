package com.zxrail.app.report.schdule;

import com.baomidou.lock.annotation.Lock4j;
import com.zxrail.framework.cache.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Slf4j
public class DailyTask {

    private static final String DEFAULT_VALUE = "1";

    private static final String INSERT_LOG_CACHE_KEY = "shopEtlByUtil";

    private static final int FIVE = 5;


    /**
     * 任务每天一点执行一次，每次记录当天设备的运行情况
     * 执行完不释放锁
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    @Lock4j(name = "insertLogByAnnotation", expire = 5 * 60 * 1000, autoRelease = false)
    public void insertLogByAnnotation() {
        // mapper.insertLog();
    }

    /**
     * 任务每天一点执行一次，每次记录当天设备的运行情况
     * 执行完不释放锁
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    public void insertLogByUtil() {
        if (!RedisUtils.setObjectIfAbsent(INSERT_LOG_CACHE_KEY, DEFAULT_VALUE, Duration.ofSeconds(FIVE))) {
            log.info("获取分布式锁失败");
            return;
        }
        // mapper.insertLog();
    }
}
