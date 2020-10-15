package com.practice.http_servlet.controller;

import com.practice.http_servlet.bean.CommonResponseBody;
import com.practice.http_servlet.delay_queue.RedisDelayQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author horizonliu
 * @date 2020/10/14 5:39 下午
 */
@RestController
@Slf4j
@RequestMapping(value = "/v1/delay_queue")
public class DelayQueueController {

    @Autowired
    private RedisDelayQueueService delayQueueService;

    @RequestMapping(value = "/redis/zset")
    public CommonResponseBody zsetDelayQueue() throws Exception {
        log.info("start zset delay queue...");
        delayQueueService.zsetMethod();
        return new CommonResponseBody();
    }

    @RequestMapping(value = "/redis/callback")
    public CommonResponseBody callbackDelayQueue() throws Exception {
        log.info("start callback delay queue...");
        delayQueueService.callbackMethod();
        return new CommonResponseBody();
    }
}
