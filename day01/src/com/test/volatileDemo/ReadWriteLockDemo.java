package com.test.volatileDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {

        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t is writting:" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t has writted.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public Object get(String key) {
        Object result = null;
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t is reading:" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t has gotten value:" + result);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return result;
    }

    public void clear() {
        map.clear();
    }
}


/**
 * @ClassName ReadWriteLockDemo
 * @Author majp
 * @Description 高并发读写锁代码验证
 * 写操作：原子性+独占，一次完成，不可分割
 * 读操作：共享
 * 运行结果（不唯一）：
 * 1	 is writting:1
 * 1	 has writted.
 * 2	 is writting:2
 * 2	 has writted.
 * 3	 is writting:3
 * 3	 has writted.
 * 4	 is writting:4
 * 4	 has writted.
 * 5	 is writting:5
 * 5	 has writted.
 * 1	 is reading:1
 * 2	 is reading:2
 * 3	 is reading:3
 * 5	 is reading:5
 * 4	 is reading:4
 * 1	 has gotten value:1
 * 2	 has gotten value:2
 * 3	 has gotten value:3
 * 5	 has gotten value:5
 * 4	 has gotten value:4
 *
 * @Date 2020-05-01 0001 17:33
 * Version 1.0
 **/
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        // 写操作
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp);
            }, String.valueOf(i)).start();
        }

        // 读操作
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp + "");
            }, String.valueOf(i)).start();
        }

    }

}
