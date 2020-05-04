package com.juc.demo;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SynchronizedDemo
 * @Author majp
 * @Description 普通锁和静态同步锁的代码验证
 * synchronized锁定的是当前类的实例对象this
 * static synchronized锁的是当前类的对象Class
 * 普通方法不在同步锁的范围内
 * @Date 2020-05-04 0004 11:48
 * Version 1.0
 **/

class Phone {
    // 普通同步方法
    public synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("invoke sendEmail(),2s");
    }

    // 普通同步方法
    public synchronized void sendMsg() {
        System.out.println("invoke sendMsg()");
    }

    // 静态同步方法
    public static synchronized void call() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("invoke static call(),2s");
    }

    // 静态同步方法
    public static synchronized void wechat() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("invoke static wechat(),1s");
    }

    // 普通方法
    public void sayHello() {
        System.out.println("invoke sayHello()");
    }

}

/**
 * synchronized锁定的是当前类的实例对象this
 * static synchronized锁的是当前类的对象Class
 * 普通方法不在同步锁的范围内
 */
public class SynchronizedDemo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
             phone.sendEmail();  // 等待2s
            phone.call(); // 等待2s
        }, "AA").start();

        Thread.sleep(100);

        new Thread(() -> {
            phone.sendMsg();
            // phone2.sendMsg();
            phone.wechat();
//            phone2.wechat();
        }, "BB").start();

        new Thread(() -> {
            phone.sayHello();
//            phone2.sayHello();
        }, "CC").start();

    }
}
