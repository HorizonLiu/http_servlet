package com.practice.http_servlet.observer;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author horizonliu
 * @date 2021/5/24 4:48 下午
 */
@Configuration
public class MyEventPublisherConfig {

    @Autowired
    private MyEventPublisher eventPublisher;

    @PostConstruct
    public void init() {
        eventPublisher.publishEvent(new MyEvent("1"));
        eventPublisher.publishEvent(new MyEvent2("2"));
    }
}
