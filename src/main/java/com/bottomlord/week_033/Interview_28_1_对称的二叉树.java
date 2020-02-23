package com.bottomlord.week_033;

import com.bottomlord.TreeNode;

/**
 * @author ThinkPad
 * @date 2020/2/23 10:40
 */
public class Interview_28_1_对称的二叉树 {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        return node1.val == node2.val && isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
    }
}
