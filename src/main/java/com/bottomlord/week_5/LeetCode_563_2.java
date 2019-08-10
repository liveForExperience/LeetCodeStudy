package com.bottomlord.week_5;

import com.bottomlord.TreeNode;

public class LeetCode_563_2 {
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return dfs(root);
    }

    private int dfs(TreeNode node) {
        int leftSum = node.left == null ? 0 : dfs(node.left);
        int rightSum = node.right == null ? 0 : dfs(node.right);

        int val = node.val;
        node.val = Math.abs(leftSum - rightSum);

        return leftSum + rightSum + val;
    }
}
