package com.bottomlord.week_011;

import com.bottomlord.TreeNode;

public class LeetCode_513_2 {
    private int maxLevel = 0;
    private int result = 0;

    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode node, int level) {
        if (node.left == null && node.right == null) {
            if (level > maxLevel) {
                maxLevel = level;
                result = node.val;
            }
            return;
        }

        if (node.left != null) {
            dfs(node.left, level + 1);
        }

        if (node.right != null) {
            dfs(node.right, level + 1);
        }
    }
}