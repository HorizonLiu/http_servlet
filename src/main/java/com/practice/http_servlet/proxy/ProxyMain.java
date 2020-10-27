package com.practice.http_servlet.proxy;

import java.lang.reflect.Proxy;

/**
 * @author horizonliu
 * @date 2020/10/27 5:39 下午
 */
public class ProxyMain {

    public static void main(String[] args) {

        UserLogger userLogger = new UserLogger();

        // 被代理者
        User user = new UserImpl();

        // 代理类，代理处理用户登录功能。在用户登录前可执行userLogger中的操作
        UserHandler userHandler = new UserHandler();
        userHandler.setMyLogger(userLogger);
        userHandler.setUser(user);

        User proxy = (User) Proxy.newProxyInstance(user.getClass().getClassLoader(), user.getClass().getInterfaces(), userHandler);
        proxy.login();
    }
}
