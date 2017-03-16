package com.hui.zhang.algorithm;

/**
 * @author zhanghui32
 * @date 2017/3/14
 */
public class LinkedList {
    private Node head = null;
    private Node last = head;

    public void add(Object data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
        } else {
            last.next = node;
        }
        last = node;
    }

    public void insert(int index, Object data) {
        Node node = head;
        int j = 0;
        while (node != null && j < index - 2) {
            node = node.next;
            j++;
        }
        Node n = new Node(data);
        n.next = node.next;
        node.next = n;
    }

    public void delete(int index, Object data) {
        Node node = head;
        int j = 0;
        while (node != null && j < index - 2) {
            node = node.next;
            j++;
        }
        node.next = node.next.next;
    }
}

class Node {
    Node next;
    Object data;

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node(Object data) {
        this.data = data;
    }
}
