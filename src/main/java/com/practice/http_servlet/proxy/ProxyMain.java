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
        UserLogAspect userHandler = new UserLogAspect();
        userHandler.setMyLogger(userLogger);
        userHandler.setObject(user);

        User proxy = (User) Proxy.newProxyInstance(User.class.getClassLoader(), new Class[]{User.class}, userHandler);
        proxy.login();
    }
}
