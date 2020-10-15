package com.practice.http_servlet.delay_queue;

import com.practice.http_servlet.repository.RedisOrderZsetRepository;
import com.practice.http_servlet.repository.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * 使用redis实现延时队列
 *
 * @author horizonliu
 * @date 2020/10/14 4:04 下午
 */
@Service
@Slf4j
public class RedisDelayQueueService {

    @Autowired
    private RedisOrderZsetRepository redisOrderZsetRepository;

    @Autowired
    private RedisRepository redisRepository;

    /**
     * 使用redis的zset数据结构实现
     * zset数据结构：key score1,value1 score2,value2
     * 每个value对应一个score，zset根据score对集合内value进行排序
     */
    @Async
    public void zsetMethod() throws Exception {
        redisOrderZsetRepository.zadd("order1", 5, TimeUnit.SECONDS);
        redisOrderZsetRepository.zadd("order2", 10, TimeUnit.SECONDS);
        redisOrderZsetRepository.zadd("order3", 15, TimeUnit.SECONDS);

        while (true) {
            Order order = redisOrderZsetRepository.zPoll();
            if (order == null) {
                log.info("当前没有订单过期");
                Thread.sleep(1000);
                continue;
            }
            if (order.getTime() < System.currentTimeMillis()) {
                log.info("订单:{}已于:{}过期", order.getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                redisOrderZsetRepository.zRemove(order.getName());
            }
            if (redisOrderZsetRepository.zCard() <= 0) {
                break;
            }
        }
    }

    /**
     * 使用redis的过期回调来时间
     */
    public void callbackMethod() {
        redisRepository.add("order1", 5, TimeUnit.SECONDS);
        redisRepository.add("order2", 10, TimeUnit.SECONDS);
        redisRepository.add("order3", 15, TimeUnit.SECONDS);
    }
}
