package com.practice.http_servlet.repository;

import com.practice.http_servlet.delay_queue.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author horizonliu
 * @date 2020/10/14 4:15 下午
 */
@Component
@Slf4j
public class RedisOrderZsetRepository {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;


    private static final String ORDER_ZSET_NAME = "order_zset";

    /**
     * 添加取消订单延迟任务
     *
     * @param orderName 订单名称
     * @param time      订单过期时间
     */
    public void zadd(String orderName, long time, TimeUnit unit) {
        Boolean flag = redisTemplate.opsForZSet().add(ORDER_ZSET_NAME, orderName, System.currentTimeMillis() + (time < 0 ? 0 : unit.toMillis(time)));
        Assert.notNull(flag, "插入数据到zset失败，返回flag为NULL");
    }

    /**
     * 获取zset的第一个元素
     *
     * @return
     */
    public Order zPoll() {
        Set<ZSetOperations.TypedTuple<Object>> eles = redisTemplate.opsForZSet().rangeWithScores(ORDER_ZSET_NAME, 0, 0);
        if (eles == null || eles.isEmpty()) {
            return null;
        }
        Object score = ((ZSetOperations.TypedTuple) eles.toArray()[0]).getScore();
        Object value = ((ZSetOperations.TypedTuple) eles.toArray()[0]).getValue();
        Assert.notNull(value, "订单名称为空");
        Assert.notNull(score, "订单过期时间为空");
        Double scoreD = (Double) score;
        return new Order((String) value, scoreD.longValue());
    }

    /**
     * 移除zset中的某个订单
     *
     * @param orderName 订单名称
     */
    public void zRemove(String orderName) {
        redisTemplate.opsForZSet().remove(ORDER_ZSET_NAME, orderName);
        log.info("remove key {} from zset {}", orderName, ORDER_ZSET_NAME);
    }

    /**
     * 获取zset的大小
     *
     * @return
     */
    public long zCard() {
        Long size = redisTemplate.opsForZSet().zCard(ORDER_ZSET_NAME);
        return size == null ? 0 : size;
    }


}
