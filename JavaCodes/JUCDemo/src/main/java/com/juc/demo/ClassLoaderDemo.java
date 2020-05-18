package com.juc.demo;

/**
 * @ClassName ClassLoaderDemo
 * @Author majp
 * @Description 类加载器demo
 * 双亲委派加载机制：启动类加载器<--扩展类加载器<--应用类加载器
 * 双亲委派机制的一个好处是不论哪个类加载器加载类，最终都是委派给顶层的启动类加载器进行加载，
 * 保证了使用不同的类加载器最终得到的都是同一个类，比如java.lang.Object,保证沙箱安全，防止恶意代码污染java源代码
 * @Date 2020-05-18 0018 8:47
 * Version 1.0
 **/

class MyObj {

}


public class ClassLoaderDemo {
    public static void main(String[] args) {
        Object object = new Object();
        MyObj myObj = new MyObj();
//        System.out.println(object.getClass().getClassLoader().getParent().getParent());
//        System.out.println(object.getClass().getClassLoader().getParent());
        System.out.println(object.getClass().getClassLoader()); // null

        System.out.println(myObj.getClass().getClassLoader().getParent().getParent()); // null
        // sun.misc.Launcher$ExtClassLoader@4554617c
        System.out.println(myObj.getClass().getClassLoader().getParent());
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(myObj.getClass().getClassLoader());
    }


}
