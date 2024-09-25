package com.bottomlord.week_011;

import com.bottomlord.TreeNode;

public class LeetCode_114_1_二叉树展开为链表 {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            TreeNode tmp = root.right;
            root.right = root.left;
            root.left = null;

            TreeNode lowestRight = dfs(root.right);
            lowestRight.right = tmp;
        }

        flatten(root.right);
    }

    private TreeNode dfs(TreeNode node) {
        if (node.right == null) {
            return node;
        }

        return dfs(node.right);
    }
}
