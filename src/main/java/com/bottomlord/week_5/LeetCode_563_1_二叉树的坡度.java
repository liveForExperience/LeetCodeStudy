package com.bottomlord.week_5;

import com.bottomlord.TreeNode;

public class LeetCode_563_1_二叉树的坡度 {
    private int sum = 0;

    public int findTilt(TreeNode root) {
        dfs(root);
        return sum;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);
        sum += Math.abs(left - right);

        return left + right + node.val;
    }
}
