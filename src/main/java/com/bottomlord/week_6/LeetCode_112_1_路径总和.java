package com.bottomlord.week_6;

import com.bottomlord.TreeNode;

public class LeetCode_112_1_路径总和 {
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, sum, 0);
    }

    private boolean dfs(TreeNode node, int sum, int add) {
        if (node == null) {
            return false;
        }

        if (node.left == null && node.right == null) {
            if (add + node.val == sum) {
                return true;
            }
        }

        return dfs(node.left, sum, add + node.val) || dfs(node.right, sum, add + node.val);
    }
}
