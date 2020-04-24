package com.test.volatileDemo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * java.util.ConcurrentModificationException
 * ArrayList线程不安全
 * 解决方法：1.使用Vector;
 *          2.使用Collections.synchronizedList(new ArrayList<>())
 */
public class ListDemo {
    public static void main(String[] args) {
        mapNotSafe();

    }

    public static void mapNotSafe() {
        // Map<String,Object> map = new HashMap<>();
        Map<String,Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i <30 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    public static void setNotSafe() {
        /**
         * HashSet底层是HashMap
         *  public HashSet() {
         *         map = new HashMap<>();
         *     }
         *
         *     public boolean add(E e) {
         *         return map.put(e, PRESENT)==null;
         *     }
         *
         */
//        Set<String> set = new HashSet<String>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        /**
         * CopyOnWriteArraySet底层是CopyOnWriteArrayList
         *   public CopyOnWriteArraySet() {
         *         al = new CopyOnWriteArrayList<E>();
         *     }
         */
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }


    public static void listNotSafe() {
        //        List<String> list = new ArrayList<String>();
//        List<String> list = new Vector<String>();
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
