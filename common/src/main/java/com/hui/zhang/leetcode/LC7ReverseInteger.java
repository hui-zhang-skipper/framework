package com.hui.zhang.leetcode;

/**
 * 反转int型数值
 */
public class LC7ReverseInteger {
    public int reverse(int x) {
        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            if (Integer.MAX_VALUE / 10 < res || (Integer.MAX_VALUE - x % 10) < res * 10) {
                return 0;
            }
            res = res * 10 + x % 10;
            x /= 10;
        }
        return sign * res;
    }
}
