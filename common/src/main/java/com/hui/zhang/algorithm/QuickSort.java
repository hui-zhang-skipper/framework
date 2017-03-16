package com.hui.zhang.algorithm;

import java.util.Arrays;

/**
 * @author zhanghui32
 * @date 2017/3/14
 */
public class QuickSort {
    public static int getMiddle(int[] numbers, int low, int high) {
        int temp = numbers[low];
        while (low < high) {
            while (low < high && numbers[high] >= temp) {
                high--;
            }
            numbers[low] = numbers[high];
            while (low < high && numbers[low] <= temp) {
                low++;
            }
            numbers[high] = numbers[low];
        }
        numbers[low] = temp;
        return low;
    }

    public static void quickSort(int[] numbers, int low, int high) {
        if (low < high) {
            int middle = getMiddle(numbers, low, high);
            quickSort(numbers, low, middle - 1);
            quickSort(numbers, middle + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {2, 3, 5, 1, 6, 3, 2, 1};
        quickSort(numbers, 0, numbers.length - 1);
        System.out.print(Arrays.toString(numbers));
    }
}
