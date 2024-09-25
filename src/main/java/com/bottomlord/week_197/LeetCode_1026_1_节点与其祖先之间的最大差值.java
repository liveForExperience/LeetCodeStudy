package com.bottomlord.week_197;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2023-04-18 19:26:22
 */
public class LeetCode_1026_1_节点与其祖先之间的最大差值 {
    private int max;

    public int maxAncestorDiff(TreeNode root) {
        this.max = 0;
        dfs(root);
        return max;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        }

        int val = node.val;
        int[] left = dfs(node.left), right = dfs(node.right);

        if (left[0] == Integer.MAX_VALUE) {
            left[0] = val;
        }

        if (left[1] == Integer.MAX_VALUE) {
            left[1] = val;
        }

        if (right[0] == Integer.MAX_VALUE) {
            right[0] = val;
        }

        if (right[1] == Integer.MAX_VALUE) {
            right[1] = val;
        }

        int min = Math.min(left[0], right[0]), max = Math.max(left[1], right[1]);
        this.max = Math.max(this.max, Math.max(Math.abs(val - min), Math.abs(val - max)));
        return new int[]{Math.min(min, val), Math.max(max, val)};
    }
}
