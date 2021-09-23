package com.example.springmvcdemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Springmvc demo 1 application.
 */
@SpringBootApplication
public class SpringmvcDemo1Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringmvcDemo1Application.class, args);
    }

    /**
     * The type Demo controller.
     */
    @RestController
    @RequestMapping("/hello")
    public class DemoController{
        /**
         * Hello world string.
         *
         * @return the string
         */
        @RequestMapping("/hello")
        public String helloWorld(){
            return "Hello Spring !!!";
        }
    }



}
