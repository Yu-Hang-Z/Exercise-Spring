package com.example.demo002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author zhangyuhang
 */
@SpringBootApplication
@EnableScheduling
public class Demo002Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo002Application.class, args); }

//    @Component
//    public class Timer{
//        @Scheduled(fixedDelay = 1000)
//        public void task(){
//            System.out.println("定时器被执行1" + new Date().toString());
//        }
//    }




}
