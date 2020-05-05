package com.juc.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName 循环屏障CyclicBarrierDemo
 * @Author majp
 * @Description CyclicBarrierDemo,与CountDownLatch相反，作加法，
 * 生活case:人到齐了才开会
 * @Date 2020-05-05 0005 13:02
 * Version 1.0
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("7人到齐，开会");
        });
        for (int i = 1; i <=7 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"已到");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
