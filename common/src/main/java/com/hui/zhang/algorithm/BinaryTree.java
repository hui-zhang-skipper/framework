package com.hui.zhang.algorithm;

/**
 * @author zhanghui32
 * @date 2017/3/14
 * 二叉树操作
 */
public class BinaryTree {
    private int data;
    private BinaryTree left;
    private BinaryTree right;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public BinaryTree(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    /**
     * 构造二查数，左子树小于结点，右子树大于结点
     *
     * @param root
     * @param data
     */
    public void insert(BinaryTree root, int data) {
        if (root != null) {
            if (data < root.data) {
                if (root.left == null) {
                    root.left = new BinaryTree(data);
                } else {
                    insert(root.left, data);
                }
            } else {
                if (root.right == null) {
                    root.right = new BinaryTree(data);
                } else {
                    insert(root.right, data);
                }
            }
        }
    }

    /**
     * 前序
     *
     * @param root
     */
    public static void preTraversal(BinaryTree root) {
        if (root != null) {
            System.out.println(root.getData() + "-");
            preTraversal(root.left);
            preTraversal(root.right);
        }
    }

    /**
     * 中序
     *
     * @param root
     */
    public static void midTraversal(BinaryTree root) {
        if (root != null) {
            preTraversal(root.left);
            System.out.println(root.getData() + "-");
            preTraversal(root.right);
        }
    }

    /**
     * 后序
     *
     * @param root
     */
    public static void sufTraversal(BinaryTree root) {
        if (root != null) {
            preTraversal(root.left);
            preTraversal(root.right);
            System.out.println(root.getData() + "-");
        }
    }
}
