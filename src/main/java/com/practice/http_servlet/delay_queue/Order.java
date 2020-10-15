package com.practice.http_servlet.delay_queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 业务内容：订单到期未支付取消，使用延迟队列进行实现
 * 方案一：采用jdk中的DelayQueue实现
 *
 * @author horizonliu
 * @date 2020/10/14 3:31 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Delayed {

    /**
     * 订单名称
     */
    private String name;

    /**
     * 订单到期时间：时间戳-单位毫秒
     */
    private long time;

    /**
     * 构造函数
     *
     * @param name 订单名称
     * @param time 订单到期时间
     * @param unit 时间单位
     */
    public Order(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Order other = (Order) o;
        if (this.time > other.time) {
            return 1;
        }
        return -1;
    }
}
