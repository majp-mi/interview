package com.juc.demo;

@FunctionalInterface
interface Foo {
    public void sayHello();

    public default int add(int x, int y) {
        return x + y;
    }

    public default int mul(int x, int y) {
        return x * y;
    }

    public static int substract(int x, int y) {
        return x - y;
    }

    public static int div(int x, int y) {
        return x / y;
    }
}

/**
 * @ClassName LambdaExpressDemo
 * @Author majp
 * @Description LambdaExpress学习
 * 1.使用前提：只有对于函数式接口@FunctionalInterface才能使用Lambda表达式创建接口对象
 * 2.拷贝小括号，写死右箭头，落地大括号
 * 3.default可多个
 * 4.static可多个
 * @Date 2020-05-02 0002 15:19
 * Version 1.0
 **/
public class LambdaExpressDemo {
    public static void main(String[] args) {
        Foo foo = ()->{
            System.out.println("hello Lambda Express"); //输入"hello Lambda Express".sout,然后回车即可
        };
        foo.sayHello();
        System.out.println(foo.add(2, 6));
        System.out.println(Foo.substract(9, 2));
    }
}
