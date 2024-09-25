package com.bottomlord.week_123;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2021-11-18 08:42:24
 */
public class LeetCode_563_1_二叉树的坡度 {
    private int sum = 0;
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }

        doFindTilt(root);

        return sum;
    }

    private int doFindTilt(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = doFindTilt(node.left), right = doFindTilt(node.right);

        sum += Math.abs(left - right);

        return node.val + left + right;
    }
}
