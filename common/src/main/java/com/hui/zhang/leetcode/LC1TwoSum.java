package com.hui.zhang.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 求两数的和等于指定值
 */
public class LC1TwoSum {

    public int[]  twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[1] = i + 1;
                result[0] = map.get(target - numbers[i]);
            }
        }
        return result;
    }
}
