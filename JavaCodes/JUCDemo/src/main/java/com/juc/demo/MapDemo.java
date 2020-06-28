package com.juc.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName MapDemo
 * @Author majp
 * @Description TODO
 * @Date 2020-05-26 0026 15:02
 * Version 1.0
 **/
public class MapDemo {
    public static void main(String[] args) {
        // 按照map插入顺序打印数据
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("a1","a1111");
        map.put("a2","a2111");
        map.put("c1","c1111");
        map.put("a3","a3111");
        map.put("b1","b1111");
        map.put("b2","b2111");
        map.put("b3","b3111");
        System.out.println(map);
        System.out.println(map.toString());
        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            Object o = iterator.next(); //执行next方法后iterator指针后移一位
            if(iterator.hasNext()){
                builder.append(map.get(o)).append("#");
            }else {
                builder.append(map.get(o));
            }

        }
        System.out.println(builder.toString());
        System.out.println("**********************");
        Map<String,Object> map1 = new HashMap<>();
        map1.put("1","00");
        // String和String类型的Object对象比较，equals会将object转成string再作比较
        System.out.println("00".equals(map1.get("1"))); // true
        System.out.println("**********************");


    }
}
