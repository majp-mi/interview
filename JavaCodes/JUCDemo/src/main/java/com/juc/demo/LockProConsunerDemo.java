package com.juc.demo;

import com.juc.demo.utils.LockProData;

/**
 * @ClassName LockProConsuner
 * @Author majp
 * @Description 生产者消费者demo
 * @Date 2020-05-04 0004 16:46
 * Version 2.0
 **/
public class LockProConsunerDemo {
    public static void main(String[] args) {
        // 定义资源类
        LockProData lockProData = new LockProData();

        // 模拟生产者
        for (int i = 1; i <= 2; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        lockProData.increase();
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
                        lockProData.decrease();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "B" + String.valueOf(i)).start();
        }
    }
}
