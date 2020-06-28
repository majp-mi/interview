package com.test.record;

/**
 * @ClassName StringDemo
 * @Author majp
 * @Description TODO
 * @Date 2020-06-28 0028 22:20
 * Version 1.0
 **/
public class StringDemo {
    public static void main(String[] args) {
        // 在字符串常量池和堆中分别创建对象，并返回堆中对象的引用
        String s1 = new String("abc");
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = "a" + "bc";// 常量找字符串常量池
        System.out.println(s1 == s2); // false
        System.out.println(s1 == s2.intern()); // false
        System.out.println(s2 == s4); // true
        String s5 = new String("ab");
        // 含堆的引用则找堆
        System.out.println(s2 == s5 + "c"); //false
    }
}
