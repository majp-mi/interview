package com.juc.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadPoolDemo
 * @Author majp
 * @Description 使用线程的四种方式：
 * 1.继承Thread类；
 * 2.实现Runnable接口；
 * 3.实现有返回值的Callable接口；
 * 4.通过创建线程池使用
 * @Date 2020-05-10 0010 14:56
 * Version 1.0
 **/
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //以下三种创建线程池的方式在实际工作中一个都不用
//        ExecutorService executor = Executors.newFixedThreadPool(3); //创建固定线程数,会导致队列积压大量请求，导致OOM
//        ExecutorService executor = Executors.newSingleThreadExecutor(); //创建单个线程数,会导致队列积压大量请求，导致OOM
        ExecutorService executor = Executors.newCachedThreadPool(); //创建可变线程数，会创建出大量的线程，导致OOM
        try {
            for (int i = 1; i <= 10; i++) {
                executor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "处理请求");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }


    }
}
