package com.example.demo005.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author by zhangyuhang
 * @Classname LogTest
 * @Description TODO
 * @Date 2021/9/26 18:00
 */
@Slf4j
public class LogTest {
    public static void printlnLogger(){
        for (int i = 0; i < 10; i++) {
            log.info("======" + i);
        }
    }

    public static void main(String[] args) {
        printlnLogger();
    }
}
