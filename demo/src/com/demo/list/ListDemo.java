package com.demo.list;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ListDemo {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3};
        List asList = Arrays.asList(arr);
        log.info("{}", asList.get(0));

        int[] arr2 = new int[]{1, 2, 3};
        List asList2 = Arrays.asList(arr2);
        log.info("{}", asList2.get(0));

        print(arr);
        print(arr2);
    }

    private static <T> void print(T... arr) {
        log.info("print:{},type:{}", arr[0], arr[0].getClass());
    }

}
