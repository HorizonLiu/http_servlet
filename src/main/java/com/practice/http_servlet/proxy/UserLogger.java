package com.practice.http_servlet.proxy;

import lombok.Data;

/**
 * 自定义日志类，用于做用户登录的记录
 *
 * @author horizonliu
 * @date 2020/10/27 5:35 下午
 */
@Data
public class UserLogger {
    private String name = "my logger";

    public void beforeLogin() {
        System.out.println("before login .......");
    }

    public void afterLogin() {
        System.out.println("after login ......");
    }
}
