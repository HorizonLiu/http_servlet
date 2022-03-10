package com.practice.http_servlet.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author horizonliu
 * @date 2021/5/24 4:21 下午
 */
@Slf4j
@Component
public class MyEvent2Listener implements ApplicationListener<MyEvent2> {

    @Override
    public void onApplicationEvent(MyEvent2 event) {
        log.info("my event2 listener");
    }
}
