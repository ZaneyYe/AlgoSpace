package com.service.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by yzy on 2016/3/3.
 */
@Component
public class Task{

    @Scheduled(cron = "0/5 * * * * ? ")
    public void taskRun(){
        System.out.println("大哥。。。。。");
    }

}
