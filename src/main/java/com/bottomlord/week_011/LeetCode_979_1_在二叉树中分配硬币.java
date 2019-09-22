package com.bottomlord.week_011;

import com.bottomlord.TreeNode;

public class LeetCode_979_1_在二叉树中分配硬币 {
    private int ans = 0;
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        ans += Math.abs(left) + Math.abs(right);
        return node.val - 1 + left + right;
    }
}
