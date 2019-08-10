package com.bottomlord.week_5;

import com.bottomlord.TreeNode;

public class LeetCode_110_2 {
    public boolean isBalanced(TreeNode root) {
        return dfs(root, 0) != -1;
    }

    private int dfs(TreeNode node, int level) {
        if (node == null) {
            return level - 1;
        }

        int left = dfs(node.left, level + 1);
        int right = dfs(node.right, level + 1);

        if (left == -1 || right == -1) {
            return -1;
        }

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right);
    }
}