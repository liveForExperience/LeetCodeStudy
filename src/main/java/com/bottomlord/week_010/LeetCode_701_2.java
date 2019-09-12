package com.bottomlord.week_010;

import com.bottomlord.TreeNode;

public class LeetCode_701_2 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode iNode = new TreeNode(val);
        if (root == null) {
            return iNode;
        }
        dfs(root, iNode);
        return root;
    }

    private void dfs(TreeNode node, TreeNode iNode) {
        if (node.val < iNode.val) {
            if (node.right != null) {
                dfs(node.right, iNode);
            } else {
                node.right = iNode;
            }
        } else {
            if (node.left != null) {
                dfs(node.left, iNode);
            } else {
                node.left = iNode;
            }
        }
    }
}