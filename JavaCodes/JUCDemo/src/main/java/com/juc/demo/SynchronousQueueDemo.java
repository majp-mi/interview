package com.juc.demo;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SynchronousQueueDemo
 * @Author majp
 * @Description 同步阻塞队列，生产一次，消费一次，依次循环
 * AAA put a
 * BBB take a
 * AAA put b
 * BBB take b
 * AAA put c
 * BBB take c
 * @Date 2020-05-05 0005 21:34
 * Version 1.0
 **/
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" put a");
                synchronousQueue.put("a");
                System.out.println(Thread.currentThread().getName()+" put b");
                synchronousQueue.put("b");
                System.out.println(Thread.currentThread().getName()+" put c");
                synchronousQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+" take a");
                synchronousQueue.take();
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+" take b");
                synchronousQueue.take();
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+" take c");
                synchronousQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }

}
