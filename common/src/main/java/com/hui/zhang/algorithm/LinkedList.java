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

    public Node findFirstCommonNode(Node node1, Node node2) {
        int length1 = getLength(node1);
        int length2 = getLength(node2);
        if (length1 == 0 || length2 == 0) {
            return null;
        }
        Node longNode, shortNode;

        if (length1 > length2) {
            longNode = node1;
            shortNode = node2;
        } else {
            longNode = node2;
            shortNode = node1;
        }
        int dif = Math.abs(length1 - length2);
        for (int i = 0; i < dif; i++) {
            longNode = longNode.next;
        }
        while (longNode != null && shortNode != null && longNode != shortNode) {
            longNode = longNode.next;
            shortNode = shortNode.next;
        }
        return longNode;
    }

    private int getLength(Node node) {
        if (node == null) {
            return 0;
        }
        Node head = node;
        int len = 0;
        while (head.next != null) {
            head = head.next;
            len++;
        }
        return len;
    }

    public static void main(String[] args) {
        int i = 6;
        Node n = new Node("2");
        Node n2 = new Node("3");


        Node nn = new Node(1);
        nn.next = new Node(2);
        nn.next.next = new Node(3);
        nn.next.next.next = new Node(4);

        n.next = nn;
        n2.next = nn;

        LinkedList linkedList = new LinkedList();
        Node n3 = linkedList.findFirstCommonNode(n, n2);
        System.out.println(n3);
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

    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                ", data=" + data +
                '}';
    }
}
