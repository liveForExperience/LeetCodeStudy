package com.bottomlord.week_011;

import com.bottomlord.TreeNode;

public class LeetCode_230_2 {
    private int count = 0;
    private int val = 0;
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return val;
    }

    private void dfs(TreeNode node, int k) {
        if (node == null) {
            return;
        }

        dfs(node.left, k);
        if (++count == k) {
            val = node.val;
            return;
        }
        dfs(node.right, k);
    }
}