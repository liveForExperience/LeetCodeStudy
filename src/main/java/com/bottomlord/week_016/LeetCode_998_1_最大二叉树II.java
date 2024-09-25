package com.bottomlord.week_016;

import com.bottomlord.TreeNode;

public class LeetCode_998_1_最大二叉树II {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val > val) {
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        }

        TreeNode node = new TreeNode(val);
        node.right = root;
        return node;
    }
}
