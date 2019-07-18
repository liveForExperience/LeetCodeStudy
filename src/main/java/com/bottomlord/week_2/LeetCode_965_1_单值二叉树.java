package com.bottomlord.week_2;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/7/18 13:13
 */
public class LeetCode_965_1_单值二叉树 {
    public boolean isUnivalTree(TreeNode root) {
        return dfs(root, root.val);
    }

    private boolean dfs(TreeNode node, int val) {
        if (node == null) {
            return true;
        }

        if (node.val != val) {
            return false;
        }

        return dfs(node.left, val) && dfs(node.right, val);
    }
}
