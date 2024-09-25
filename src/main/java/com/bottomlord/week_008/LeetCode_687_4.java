package com.bottomlord.week_008;

import com.bottomlord.TreeNode;

public class LeetCode_687_4 {
    private int count = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, root.val);
        return count;
    }

    private int dfs(TreeNode node, int preVal) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left, node.val);
        int right = dfs(node.right, node.val);

        count = Math.max(count, left + right);
        return node.val == preVal ? Math.max(left, right) + 1 : 0;
    }
}