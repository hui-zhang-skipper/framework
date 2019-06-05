package com.hui.zhang.leetcode;

import com.alibaba.fastjson.JSON;

/**
 * Created by zhanghui on 2018/1/8.
 */
public class LC66PlusOne {

    public static int[] plusOne(int[] digits) {
        int carry = 1;
        int temp;
        for (int i = digits.length - 1; i >= 0; i--) {
            temp = digits[i];
            digits[i] = (temp + carry) % 10;
            carry = (temp + carry) / 10;
            if (carry == 0) break;

        }
        if (carry == 1) {
            int[] result = new int[digits.length + 1];
            result[0]=carry;
            System.arraycopy(digits, 0, result, 1, digits.length);
            return result;
        }
        return digits;
    }

    public static void main(String[] args) {
        int[] arr = {9, 9, 9, 9};
        System.out.printf(JSON.toJSONString(plusOne(arr)));
    }
}
