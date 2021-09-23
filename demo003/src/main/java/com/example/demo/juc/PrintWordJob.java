package com.example.demo.juc;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author zhangyuhang
 */
@Data
@Component
public class PrintWordJob extends Thread {
    private String title;
    public PrintWordJob(String title){
        this.title = title;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            System.out.println(this.title+"运行-------"+i);
        }

    }
}
