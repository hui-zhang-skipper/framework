package com.hui.zhang.leetcode;

/**
 * 次幂和开方
 */
public class LC50Pow {

    public static double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        double ans = 1;
        while (n > 0) {
            ans *= x;
            n--;
        }
        return ans;
    }

    public static int mySqrt(int x) {
        double low = 0;
        double high = x;
        while (low < high) {
            double mid = (low + high) / 2;
            if (Math.abs(mid * mid - x) < 0.01) {
                return new Double(mid).intValue();
            } else if (mid * mid < x) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return new Double(low).intValue();

    }

    public static void main(String[] args) {
        double d = mySqrt(16);
        System.out.println(d);
    }
}
