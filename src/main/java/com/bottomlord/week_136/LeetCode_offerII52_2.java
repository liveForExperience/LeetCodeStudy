package com.bottomlord.week_136;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2022-02-15 09:36:00
 */
public class LeetCode_offerII52_2 {
    private TreeNode iter;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode head = new TreeNode(-1);
        iter = head;
        inOrder(root);
        return head.right;
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);

        node.left = null;
        iter.right = node;
        iter = node;

        inOrder(node.right);
    }
}
