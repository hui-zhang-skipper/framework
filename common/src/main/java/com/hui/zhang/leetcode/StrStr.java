package com.hui.zhang.leetcode;

/**
 * Created by zhanghui on 2018/1/7.
 * 一个字符串在另一个字符串中的位置
 */
public class StrStr {

    public int strStr(String haystack, String needle) {
        int l1 = haystack.length(), l2 = needle.length();
        if (l1 < l2) return -1;
        else if (l2 == 0) return 0;

        int threshold = l1 - l2;
        for (int i = 0; i <= threshold; ++i) {
            if (haystack.substring(i, i + l2).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
