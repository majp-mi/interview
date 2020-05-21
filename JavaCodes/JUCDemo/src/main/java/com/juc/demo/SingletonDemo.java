package com.juc.demo;

/**
 * @ClassName SingletonDemo
 * @Author majp
 * @Description 单例模式demo
 * @Date 2020-05-21 0021 18:39
 * Version 1.0
 **/
public class SingletonDemo {
    private static volatile SingletonDemo singletonDemo = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "线程执行单例构造方法");
    }

    // DCL
    public static SingletonDemo getInstance() {
        if (singletonDemo == null) {
            synchronized (SingletonDemo.class) {
                if (singletonDemo == null) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }

}
