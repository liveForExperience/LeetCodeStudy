package com.bottomlord.week_019;

import com.bottomlord.TreeNode;

public class LeetCode_1026_1_节点与其祖先之间的最大差值 {
    private int ans = 0;
    public int maxAncestorDiff(TreeNode root) {
        dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return ans;
    }

    private void dfs(TreeNode node, int max, int min) {
        if (node == null) {
            return;
        }

        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        ans = Math.max(ans, max - min);

        dfs(node.left, max, min);
        dfs(node.right, max, min);
    }
}
