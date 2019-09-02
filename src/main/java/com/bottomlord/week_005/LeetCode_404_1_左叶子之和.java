package com.bottomlord.week_005;

import com.bottomlord.TreeNode;

public class LeetCode_404_1_左叶子之和 {
    private int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root, false);
        return sum;
    }

    private void dfs(TreeNode node, boolean isLeft) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            if (isLeft) {
                sum += node.val;
            }
            return;
        }
        dfs(node.left, true);
        dfs(node.right, false);
    }
}
