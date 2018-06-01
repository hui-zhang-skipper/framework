package com.hui.zhang.leetcode;

/**
 * Created by zhanghui on 2018/6/1.
 */
public class ArrayOfSonMax {

    public static int maxSum(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i != array.length; i++) {
            cur += array[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    public static void main() {
        int[] array = new int[]{1, -2, 3, 5, -2, 6, -1};
        System.out.printf(maxSum(array) + "");
    }
}
