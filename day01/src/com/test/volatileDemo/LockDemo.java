package com.test.volatileDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized，ReentrantLock非公平可重入锁（递归锁）
 * 可重入锁就是递归锁
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取到该锁的代码，在同一线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 * 也就是说：线程可以进入任何一个它已经拥有的锁所同步的代码块
 */
class Phone implements Runnable {
    public synchronized void sendMsg() {
        System.out.println(Thread.currentThread().getName() + "\t" + "phone invoke sendMsg");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t" + "phone invoke sendEmail");
    }

    @Override
    public void run() {
        set();
    }

    public void set() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "phone invoke set");
            get();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "phone invoke get");
        } finally {
            lock.unlock();
        }
    }
}

public class LockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        // synchronized可重入锁代码演示
        /**
         * t1	phone invoke sendMsg
         * t1	phone invoke sendEmail
         * t2	phone invoke sendMsg
         * t2	phone invoke sendEmail
         */
        new Thread(() -> {
            phone.sendMsg();
        }, "t1").start();

        new Thread(() -> {
            phone.sendMsg();
        }, "t2").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("******************************");
        System.out.println("******************************");
        // ReentrantLock可重入锁代码演示
        /**
         * t4	phone invoke set
         * t4	phone invoke get
         * t3	phone invoke set
         * t3	phone invoke get
         */
        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");
        t3.start();
        t4.start();

    }
}
