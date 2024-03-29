package com.demo.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author youxin
 */
public class StreamTest {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("张三", "李四", "王老五", "李三", "刘老四", "王小二", "张四", "张五六七");

        String maxLenStartWithZ = names.stream()
                .filter(name -> name.startsWith("张"))
                .mapToInt(String::length)
                .max()
                .toString();

        System.out.println(maxLenStartWithZ);

    }

}
