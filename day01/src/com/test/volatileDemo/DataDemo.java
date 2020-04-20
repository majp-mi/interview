package com.test.volatileDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile作用：
 * 1.可见性
 * 2.不保证原子性
 * 3.禁止指令重排
 */
class VolatileDemo {
    volatile int num = 0;
    AtomicInteger atomicInteger = new AtomicInteger();

    public void updateNum(int c) {
        this.num = c;
    }

    //num++不保证原子性
    public void addNum() {
        num++;
    }

    //保证原子性
    public void addAtomInteger() {
        atomicInteger.getAndIncrement();
    }
}

public class DataDemo {
    public static void main(String[] args) {
        volatileTest();
        volatileTest2();
    }

    public static void volatileTest2() {
        VolatileDemo volatileDemo = new VolatileDemo();
        // 创建20个线程，每个线程累加1000次
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    volatileDemo.addNum();
                    volatileDemo.addAtomInteger();
                }
            }, "子线程" + String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("num = " + volatileDemo.num);
        System.out.println("num = " + volatileDemo.atomicInteger);
    }

    /**
     * volatile保证可见性
     */
    public static void volatileTest() {
        VolatileDemo dataDemo = new VolatileDemo();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dataDemo.updateNum(60);
            System.out.println("num = " + dataDemo.num);
        }, "AAA").start();
        while (dataDemo.num == 0) {
        }
        System.out.println("main 执行完成");
    }
}
