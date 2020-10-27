package com.practice.http_servlet.proxy;

/**
 * @author horizonliu
 * @date 2020/10/27 5:33 下午
 */
public class UserImpl implements User {
    @Override
    public void login() {
        System.out.println("User login success");
    }
}
