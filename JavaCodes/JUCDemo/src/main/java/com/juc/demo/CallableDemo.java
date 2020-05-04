package com.juc.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CallableDemo
 * @Author majp
 * @Description 有返回值的线程demo使用
 * 凡是跟线程有关的类都需要通过new Thread().start()来启动
 * @Date 2020-05-04 0004 23:07
 * Version 1.0
 **/

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("invoke call()..");
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // FutureTask--->RunnableFuture ——>Runnable
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask,"A").start();
        Integer result = futureTask.get();
        System.out.println(result);
    }

}
