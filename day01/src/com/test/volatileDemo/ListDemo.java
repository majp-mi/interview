package com.test.volatileDemo;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList线程不安全
 * 解决方法：1.使用Vector;
 *          2.使用Collections.synchronizedList(new ArrayList<>())
 */
public class ListDemo {
    public static void main(String[] args) {

       // java.util.ConcurrentModificationException
//        List<String> list = new ArrayList<String>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
