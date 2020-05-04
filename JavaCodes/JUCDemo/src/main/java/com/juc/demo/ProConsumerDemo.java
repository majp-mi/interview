package com.juc.demo;

import com.juc.demo.utils.ProData;

/**
 * @ClassName ProConsumerDemo
 * @Author majp
 * @Description 生产者消费者demo
 * @Date 2020-05-04 0004 16:26
 * Version 1.0
 **/


public class ProConsumerDemo {
    public static void main(String[] args) {
        // 定义资源类
        ProData airCondition = new ProData();
        // 模拟生产者
        for (int i = 1; i <= 2; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        airCondition.increase();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "A" + String.valueOf(i)).start();
        }

        // 模拟消费者
        for (int i = 1; i <= 2; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        airCondition.decrease();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "B" + String.valueOf(i)).start();
        }
    }

}
