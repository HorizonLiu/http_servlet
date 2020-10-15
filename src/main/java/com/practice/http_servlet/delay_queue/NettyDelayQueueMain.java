package com.practice.http_servlet.delay_queue;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 基于时间轮的延时队列
 *
 * @author horizonliu
 * @date 2020/10/15 11:21 上午
 */
public class NettyDelayQueueMain {
    public static void main(String[] args) {
        // 创建时间轮，每个刻度5s，一个时间轮有2个刻度（10s）
        final Timer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 5, TimeUnit.SECONDS, 2);

        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order1 5s 后执行，当前时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                // 执行完之后再次注册定时事件
                timer.newTimeout(this, 5, TimeUnit.SECONDS);
            }
        };
        // 首次注册定时事件
        timer.newTimeout(task, 5, TimeUnit.SECONDS);

        TimerTask task2 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order2 10s 后执行，当前时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                timer.newTimeout(this, 10, TimeUnit.SECONDS);
            }
        };
        timer.newTimeout(task2, 10, TimeUnit.SECONDS);

        // task3只执行一次就结束
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order3 15s 后执行，当前时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        }, 15, TimeUnit.SECONDS);

    }
}
