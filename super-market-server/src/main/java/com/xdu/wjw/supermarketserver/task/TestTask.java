package com.xdu.wjw.supermarketserver.task;

import com.xdu.wjw.supermarketserver.util.LogUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Class: TestTask
 * @Author: Wei Junwei
 * @Time: 2022/8/16 23:00
 * @Description:
 */
@Component
@EnableScheduling
public class TestTask {

    @Scheduled(cron = "0/10 * * * * ?")
    public void test() {
        LogUtil.getScheduleTaskLogger().info("hello scheduled!");
    }
}
