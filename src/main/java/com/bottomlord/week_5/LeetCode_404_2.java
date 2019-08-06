package com.bottomlord.week_5;

import com.bottomlord.TreeNode;

public class LeetCode_404_2 {
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }

    private int dfs(TreeNode node, boolean isLeft) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            if (isLeft) {
                return node.val;
            }

            return 0;
        }

        return dfs(node.left, true) + dfs(node.right, false);
    }
}