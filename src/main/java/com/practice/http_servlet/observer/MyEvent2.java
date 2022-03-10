package com.practice.http_servlet.observer;

import org.springframework.context.ApplicationEvent;

/**
 * @author horizonliu
 * @date 2021/5/24 4:19 下午
 */
public class MyEvent2 extends ApplicationEvent {

    private static final long serialVersionUID = 1125124017106590616L;

    public MyEvent2(Object source) {
        super(source);
    }
}
