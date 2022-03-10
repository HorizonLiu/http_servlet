package com.practice.http_servlet.observer;

import org.springframework.context.ApplicationEvent;

/**
 * @author horizonliu
 * @date 2021/5/24 4:16 下午
 */
public class MyEvent extends ApplicationEvent {

    private static final long serialVersionUID = -811105570285993365L;

    public MyEvent(Object source) {
        super(source);
    }
}
