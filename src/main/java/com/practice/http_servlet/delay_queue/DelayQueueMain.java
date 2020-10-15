package com.practice.http_servlet.delay_queue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 参考博客：https://juejin.im/post/6844904150703013901
 *
 * @author horizonliu
 * @date 2020/10/14 3:47 下午
 */
public class DelayQueueMain {

    /**
     * 采用DelayQueue的缺点：
     * 1. 在内存中进行入队操作，大数据量情况下无法hold
     *
     * @param args 程序入口参数
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Order order1 = new Order("order1", 5, TimeUnit.SECONDS);
        Order order2 = new Order("order2", 10, TimeUnit.SECONDS);
        Order order3 = new Order("order3", 15, TimeUnit.SECONDS);

        DelayQueue<Order> delayQueue = new DelayQueue<>();
        // delayQueue的put方法是线程安全的
        delayQueue.put(order1);
        delayQueue.put(order2);
        delayQueue.put(order3);

        System.out.println("订单延迟队列开始时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        while (delayQueue.size() != 0) {
            /**
             * 取队列头部元素是否过期
             * 未到期的元素返回null
             */
            Order task = delayQueue.poll();
            if (task != null) {
                System.out.format("订单:{%s}被取消, 取消时间:{%s}\n", task.getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            Thread.sleep(1000);
        }
    }
}
