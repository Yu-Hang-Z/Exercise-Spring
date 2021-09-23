package com.example.demo002.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhangyuhang
 */
@Component
@Slf4j
public class RunIntervalTestScheduler {

    @Scheduled(cron = "0/1 * * * * ?")
    public void singleThreadTest1() {
        log.info("singleThreadTest1");
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void singleThreadTest2() {
        log.info("singleThreadTest2");
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void singleThreadTest3() {
        log.info("singleThreadTest3");
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
    }
}
