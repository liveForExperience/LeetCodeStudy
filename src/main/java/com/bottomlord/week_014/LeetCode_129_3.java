package com.bottomlord.week_014;

import com.bottomlord.TreeNode;

public class LeetCode_129_3 {
    private int sum = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode node, int num) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            sum += num + node.val;
            return;
        }

        dfs(node.left, num * 10 + node.val * 10);
        dfs(node.right, num * 10 + node.val * 10);
    }
}
