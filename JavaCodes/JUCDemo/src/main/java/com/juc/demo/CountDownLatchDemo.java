package com.juc.demo;

import com.juc.demo.utils.CountryEnum;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CountDownLatchDemo
 * @Author majp
 * @Description TODO
 * @Date 2020-05-02 0002 9:41
 * Version 1.0
 **/


/**
 * 运行结果（被灭国家顺序不唯一）：
 * 齐国被灭
 * 楚国被灭
 * 燕国被灭
 * 韩国被灭
 * 赵国被灭
 * 魏国被灭
 * 秦国一统华夏
 */
public class CountDownLatchDemo {
    private static final Integer count = 6;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(count);

        for (int i = 1; i <= count; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "国被灭");
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }, CountryEnum.searchCountryEnum(i).getRetMsg()).start();
        }

        countDownLatch.await();
        System.out.println("秦国一统华夏");
        System.out.println(CountryEnum.ONE); //ONE
        System.out.println(CountryEnum.ONE.getRetCode()); //1
        System.out.println(CountryEnum.ONE.getRetMsg()); //齐

    }
}
