package com.example.demo.juc;

import org.springframework.stereotype.Component;

/**
 * @author zhangyuhang
 * @Classname ThreadDemo
 * @Description TODO
 * @Date 2021/8/1 21:23
 * @Created by zhangyuhang
 */
@Component
public class ThreadDemo {
    public static void main(String[] args) {
        new PrintWordJob("线程A").start();
        new PrintWordJob("线程B").start();
        new PrintWordJob("线程C").start();

    }
}
