package com.bottomlord.week_014;

import com.bottomlord.TreeNode;

public class LeetCode_129_2 {
    private int sum = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root, "");
        return sum;
    }

    private void dfs(TreeNode node, String num) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            sum += Integer.parseInt(num + node.val);
            return;
        }

        dfs(node.left, num + node.val);
        dfs(node.right, num + node.val);
    }
}