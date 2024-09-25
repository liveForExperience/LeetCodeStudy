package com.bottomlord.week_005;

import com.bottomlord.TreeNode;

public class LeetCode_110_1_平衡二叉树 {
    private boolean result = true;
    public boolean isBalanced(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    private int dfs(TreeNode node, int level) {
        if (node == null) {
            return level - 1;
        }

        int left = dfs(node.left, level + 1);
        int right = dfs(node.right, level + 1);

        this.result = this.result && Math.abs(left - right) <= 1;

        return Math.max(left, right);
    }
}
