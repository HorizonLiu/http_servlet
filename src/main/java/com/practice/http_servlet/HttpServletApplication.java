package com.practice.http_servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HttpServletApplication {


    public static void main(String[] args) {
        SpringApplication.run(HttpServletApplication.class, args);
    }
}
