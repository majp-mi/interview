package com.juc.demo;

/**
 * @ClassName ClassLoaderDemo
 * @Author majp
 * @Description 类加载器demo
 * 双亲委派加载机制：启动类加载器<--扩展类加载器<--应用类加载器
 * 双亲委派机制的一个好处是不论哪个类加载器加载类，最终都是委派给顶层的启动类加载器进行加载，
 * 保证了使用不同的类加载器最终得到的都是同一个类，比如java.lang.Object,保证沙箱安全，防止恶意代码污染java源代码
 *
 * JAVA堆，所有线程共享，存在垃圾回收（方法区也是共享，存在少量的垃圾回收，主要还是堆的垃圾回收）：分为年轻代，占1/3，老年代，占2/3；年轻代分为伊甸园区，FROM区和TO区，FROM和TO区相互交换，三者的比例默认是8：1：1
 * java7及以前分年轻代，老年代和永久代，java8及以后，永久代被元空间替换，且元空间被移到方法区
 * 分代收集算法：
 * 1.引用计数法，JVM不用
 * 2.复制算法，在年轻代中使用该GC算法，效率高，没有内存碎片，但占空间
 * 3.标记清除算法  节省空间，但耗时
 * 4.标记压缩算法   标记带回收对象，并将他们移动到一段连续的空间后再回收，存在内存移动，比标记清除算法更耗时
 * 3、4算法一般在老年区混合使用
 *
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
