package com.hui.zhang.leetcode;

/**
 * Created by zhanghui on 2018/1/7.
 * 两个有序链表合并
 */
public class MergeTwoSortedList {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode tail = head;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = (l1 != null ? l1 : l2);
        return head.next;
    }

    public static void main(String[] args){
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(3);
        MergeTwoSortedList mergeTwoSortedList=new MergeTwoSortedList();
        ListNode l3= mergeTwoSortedList.mergeTwoLists(l1,l2);
        System.out.println(l3);
    }

}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
        this.val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
