package com.juc.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ProdConsumerV3Demo
 * @Author majp
 * @Description 生产者消费者阻塞队列版
 * @Date 2020-05-07 0007 16:17
 * 运行结果（不唯一）：
 * prod插入队列1成功
 * consumer消费队列1
 * prod插入队列2成功
 * consumer消费队列2
 * prod插入队列3成功
 * consumer消费队列3
 * prod插入队列4成功
 * consumer消费队列4
 * prod插入队列5成功
 * consumer消费队列5
 * prod插入队列6成功
 * consumer消费队列6
 * prod插入队列7成功
 * consumer消费队列7
 * prod插入队列8成功
 * consumer消费队列8
 * 主线程等待10s控制退出
 * consumer超过4s没有取到数据，消费退出
 * Version 1.0
 **/

class MyResource {
    private volatile boolean flag = true; //默认开启，生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void prod() throws InterruptedException {
        String data = null;
        while (flag) {
            data = atomicInteger.incrementAndGet() + "";
            boolean result = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (result) {
                System.out.println(Thread.currentThread().getName() + "插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "插入队列" + data + "失败");
            }

            TimeUnit.SECONDS.sleep(2);
        }
    }

    public void consume() throws InterruptedException {
        String data = null;
        while (flag) {
            data = blockingQueue.poll(4,TimeUnit.SECONDS);
            if (null == data || "".equals(data)) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + "超过4s没有取到数据，消费退出");
                return;
            } else {
                System.out.println(Thread.currentThread().getName() + "消费队列" + data);
            }
        }
    }

    public void stop() {
        flag = false;
    }

}

public class ProdConsumerV3Demo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            try {
                myResource.prod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "prod").start();

        new Thread(() -> {
            try {
                myResource.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "consumer").start();
        try {
            TimeUnit.SECONDS.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myResource.stop();
        System.out.println("主线程等待10s控制退出");
    }
}
