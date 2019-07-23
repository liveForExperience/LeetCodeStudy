package com.bottomlord.week_3;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/7/23 12:54
 */
public class LeetCode_669_1_修剪二叉搜索树 {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }

        if (root.val < L) {
            return trimBST(root.right, L, R);
        }

        if (root.val > R) {
            return trimBST(root.left, L, R);
        }

        TreeNode node = new TreeNode(root.val);
        node.left = trimBST(root.left, L, R);
        node.right = trimBST(root.right, L, R);
        return node;
    }
}
