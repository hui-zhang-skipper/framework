package com.hui.zhang.algorithm;

import java.util.*;

/**
 * 频率
 */
public class RateCount {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 5, 2, 3, 6, 7, 2, 4, 2, 8, 4, 3, 2};
        int[] rateValue = getRateCount(array);
        System.out.printf("出现次数最多的值:" + rateValue[0] + ",出现了" + rateValue[1] + "次");

        getCount();
    }

    public static int getCount() {
        int count = 0;
        for (int i = 1; i < 60; i++) {
            for (int j = 0; j < 24; j++) {
                if ((int) ((i / 60.0 + j) * 5) == i) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int[] getRateCount(int[] array) {
        int[] b = new int[10];
        for (int i = 0; i < array.length; i++) {
            b[array[i]]++;
        }
        int max = b[0];//定义次数
        int value = 0;//定义最大值
        for (int i = 1; i < b.length; i++) {
            if (b[i] > max) {
                max = b[i];
                value = i;
            }
        }
        return new int[]{max, value};

    }

    public static int[] methodRate(int[] array) {
        HashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int a : array) {
            if (map.containsKey(a)) {
                int temp = map.get(a);
                map.put(a, temp + 1);
            } else {
                map.put(a, 1);
            }
        }

        Collection<Integer> count = map.values();
        int maxCount = Collections.max(count);
        int maxNumber = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (maxCount == entry.getValue()) {
                maxNumber = entry.getKey();
                break;
            }
        }
        return new int[]{maxNumber, maxCount};
    }
}
