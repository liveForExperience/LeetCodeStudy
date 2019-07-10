package com.bottomlord.week_1;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/7/10 13:15
 */
public class LeetCode_226_1_翻转二叉树 {
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    private void invert(TreeNode node) {
        if (node == null) {
            return;
        }

        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;

        invert(node.left);
        invert(node.right);
    }
}
