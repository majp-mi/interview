package com.juc.demo.utils;

/**
 * @ClassName ProData
 * @Author majp
 * @Description 操作资源类
 * @Date 2020-05-04 0004 17:04
 * Version 1.0
 **/
public class ProData {
    private int number = 0;

    public synchronized void increase() throws InterruptedException {
        /**
         * 判断，为防止线程虚假唤醒，多线程的横向通信调用，判断要用while判断替换if作判断
         * wait和notifyAll只能配合synchronized使用
         */

        while (number != 0) {
            this.wait();
        }
        // 干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        // 通知
        this.notifyAll();
    }

    public synchronized void decrease() throws InterruptedException {
        while (number != 1) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }
}
