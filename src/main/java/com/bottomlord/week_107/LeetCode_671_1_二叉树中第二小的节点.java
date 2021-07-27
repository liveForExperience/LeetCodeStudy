package com.bottomlord.week_107;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2021/7/27 8:51
 */
public class LeetCode_671_1_二叉树中第二小的节点 {
    public int findSecondMinimumValue(TreeNode root) {
        int val = dfs(root, root.val);
        return val == Integer.MAX_VALUE ? -1 : val;
    }

    private int dfs(TreeNode node, int min) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        if (node.left == null && node.right == null) {
            return node.val == min ? Integer.MAX_VALUE : node.val;
        }

        return Math.min(dfs(node.left, min), dfs(node.right, min));
    }
}
