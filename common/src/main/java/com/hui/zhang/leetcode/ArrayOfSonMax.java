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

    public static void main(String[] args) {
        Double payOrderAmt=85.0;
        Double doormanEstimatedAmount=85.0;
        Double payAmt=85.0;
        if (payOrderAmt.equals(doormanEstimatedAmount) && payAmt > 0) {
            System.out.printf("true");
        }
        System.out.printf("false");

        Double index=1.0;
        Double index2=1.0;
        if (index==index2){
            System.out.printf("1");
        }
    }
}
