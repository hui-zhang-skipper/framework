package com.hui.zhang.leetcode;

import com.hui.zhang.leetcode.node.TreeNode;

public class LC104MaximumDepthofBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
