package com.practice.http_servlet.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author horizonliu
 * @date 2020/10/14 4:15 下午
 */
@Component
@Slf4j
public class RedisRepository {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 添加元素
     *
     * @param orderName 订单名称
     * @param time      订单失效时长
     * @param unit      时间单位
     */
    public void add(String orderName, long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(orderName, 1, time, TimeUnit.SECONDS);
    }

}
