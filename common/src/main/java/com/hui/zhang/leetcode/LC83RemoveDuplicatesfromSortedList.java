package com.hui.zhang.leetcode;

import com.hui.zhang.leetcode.node.ListNode;

/**
 * 有序链表去重
 */
public class LC83RemoveDuplicatesfromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        String source = "abccdeefggh";

        StringBuilder temp = new StringBuilder(source.substring(0, 1));

        int len = source.length();

        int k = 0;
        for (int i = 1; i < len; i++) {
            if (source.substring(i, i + 1).equals(temp.substring(k, k + 1))) {
                continue;
            }
            temp.append(source.substring(i,i+1));
            k++;
        }
    }
}


