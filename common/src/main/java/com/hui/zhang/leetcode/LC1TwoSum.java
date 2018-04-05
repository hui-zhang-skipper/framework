package com.hui.zhang.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 求两数的和等于指定值
 */
public class LC1TwoSum {

    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[1] = i;
                result[0] = map.get(target - numbers[i]);
            }
            else {
                map.put(numbers[i],i);
            }
        }
        return result;
    }

    public static void main(String[] args){
        int[] number=new int[]{2,7,9,12};
        int[] a=twoSum(number,14);
        System.out.println(JSON.toJSON(a));
    }
}
