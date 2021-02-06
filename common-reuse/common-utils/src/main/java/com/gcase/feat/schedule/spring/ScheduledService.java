package com.gcase.feat.schedule.spring;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//<dependency>
//    <groupId>org.springframework.boot</groupId>
//    <artifactId>spring-boot-starter-web</artifactId>
//    <version>2.1.17.RELEASE</version>
//</dependency>
/**
 * <p>
 * 定时任务服务
 * <p>
 *
 * @creator bicheng.deng
 * @createTime 2021/2/3
 */
@Component
public class ScheduledService {
    
    @Scheduled(cron = "0-50 * * * * ?")
    public void testJob(){
        System.out.println("定时任务执行");
    }
}
