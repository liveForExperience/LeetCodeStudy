package com.bottomlord.week_006;

import com.bottomlord.TreeNode;

public class LeetCode_543_1_二叉树的直径 {
    private int max = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        max = Math.max(left + right, max);
        return Math.max(left, right) + 1;
    }
}
