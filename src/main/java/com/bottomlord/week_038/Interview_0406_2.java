package com.bottomlord.week_038;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/3/25 8:16
 */
public class Interview_0406_2 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode left = inorderSuccessor(root.left, p);
            return left == null ? root : left;
        }
    }
}