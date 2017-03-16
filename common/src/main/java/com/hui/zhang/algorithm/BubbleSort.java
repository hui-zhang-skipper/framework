package com.hui.zhang.algorithm;

import java.util.Arrays;

/**
 * @author zhanghui32
 * @date 2017/3/14
 * 冒泡排序
 */
public class BubbleSort {
    public static void bubbleSort(int[] numbres) {
        int temp;
        int size = numbres.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (numbres[j] > numbres[j + 1]) {
                    temp = numbres[j];
                    numbres[j] = numbres[j + 1];
                    numbres[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {2, 3, 5, 1, 6, 3, 2, 1};
        bubbleSort(numbers);
        System.out.print(Arrays.toString(numbers));

    }
}
