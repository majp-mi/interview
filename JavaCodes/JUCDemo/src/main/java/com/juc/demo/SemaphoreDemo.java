package com.juc.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SemaphoreDemo
 * @Author majp
 * @Description 信号灯SemaphoreDemo
 * 多个车抢多个车位，车的数量大于车位数量
 * @Date 2020-05-05 0005 13:20
 * Version 1.0
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"抢到车位");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"停留3秒离开车位");
                semaphore.release();
            },String.valueOf(i)).start();
        }
    }

}
