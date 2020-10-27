package com.practice.http_servlet.proxy;

import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 实现代理类，在用户登录前后增加执行逻辑
 *
 * @author horizonliu
 * @date 2020/10/27 5:34 下午
 */
@Data
public class UserHandler implements InvocationHandler {

    /**
     * 日志处理
     */
    private UserLogger myLogger;

    /**
     * 用户类
     */
    private User user;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        myLogger.beforeLogin();
        Object res = method.invoke(user, args);
        myLogger.afterLogin();
        return res;
    }
}
