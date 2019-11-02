package com.bottomlord.week_017;

import com.bottomlord.TreeNode;

public class LeetCode_337_2 {
    public int rob(TreeNode root) {
        int[] ans = dfs(root);
        return Math.max(ans[0], ans[1]);
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int[] cur = new int[2];
        cur[0] = Math.max(left[0], left[0]) + Math.max(right[0], right[1]);
        cur[1] = left[0] + right[1] + node.val;

        return cur;
    }
}