package com.bottomlord.week_035;

import com.bottomlord.TreeNode;

/**
 * @author ThinkPad
 * @date 2020/3/6 8:53
 */
public class Interview_55II_1_平衡二叉树 {
    public boolean isBalanced(TreeNode root) {
        return dfs(root) > 0;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        int left = dfs(node.left),
            right = dfs(node.right);

        if (left < 0 || right < 0) {
            return -1;
        }

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;
    }
}
