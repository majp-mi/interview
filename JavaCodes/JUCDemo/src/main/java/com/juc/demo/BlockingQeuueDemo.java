package com.juc.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BlockingQeuueDemo
 * @Author majp
 * @Description 阻塞队列四种类型方法演示
 * @Date 2020-05-05 0005 21:05
 * Version 1.0
 **/
public class BlockingQeuueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        // 异常类型方法
        System.out.println(blockingQueue.add("a")); //true
        System.out.println(blockingQueue.add("b")); //true
        System.out.println(blockingQueue.add("c")); //true
        // Exception in thread "main" java.lang.IllegalStateException: Queue full
//        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.remove()); //a
        System.out.println(blockingQueue.remove()); //b
        System.out.println(blockingQueue.remove()); //c
        // Exception in thread "main" java.util.NoSuchElementException
//        System.out.println(blockingQueue.remove());

        BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<String>(3);
        // 布尔类型方法
        System.out.println(blockingQueue2.offer("1")); //true
        System.out.println(blockingQueue2.offer("1")); //true
        System.out.println(blockingQueue2.offer("1")); //true
        System.out.println(blockingQueue2.offer("1")); //false
        System.out.println(blockingQueue2.poll()); //1
        System.out.println(blockingQueue2.poll());//1
        System.out.println(blockingQueue2.poll());//1
        System.out.println(blockingQueue2.poll()); //null

        BlockingQueue<String> blockingQueue3 = new ArrayBlockingQueue<String>(3);
        //阻塞类型方法
        blockingQueue3.put("AA");
        blockingQueue3.put("AA");
        blockingQueue3.put("AA");
        // 一直阻塞
//        blockingQueue3.put("AA");
        System.out.println(blockingQueue3.take()); //AA
        System.out.println(blockingQueue3.take());//AA
        System.out.println(blockingQueue3.take());//AA
        // 一直阻塞
//        System.out.println(blockingQueue3.take());

        BlockingQueue<String> blockingQueue4 = new ArrayBlockingQueue<String>(3);
        // 超时类型方法
        System.out.println(blockingQueue4.offer("123", 2, TimeUnit.SECONDS)); //true
        System.out.println(blockingQueue4.offer("123", 2, TimeUnit.SECONDS));//true
        System.out.println(blockingQueue4.offer("123", 2, TimeUnit.SECONDS));//true
        System.out.println(blockingQueue4.offer("123", 2, TimeUnit.SECONDS));//2s后输出false

        System.out.println(blockingQueue4.poll(2, TimeUnit.SECONDS));//123
        System.out.println(blockingQueue4.poll(2, TimeUnit.SECONDS));//123
        System.out.println(blockingQueue4.poll(2, TimeUnit.SECONDS));//123
        System.out.println(blockingQueue4.poll(2, TimeUnit.SECONDS));//2s后输出null

    }


}
