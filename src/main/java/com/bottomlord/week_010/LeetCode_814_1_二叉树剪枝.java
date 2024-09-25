package com.bottomlord.week_010;

import com.bottomlord.TreeNode;

public class LeetCode_814_1_二叉树剪枝 {
    public TreeNode pruneTree(TreeNode root) {
        return dfs(root);
    }

    private TreeNode dfs(TreeNode node) {
        if (node == null) {
            return null;
        }

        node.left = dfs(node.left);
        node.right = dfs(node.right);
        if (node.left == null && node.right == null && node.val == 0) {
            return null;
        }

        return node;
    }
}
