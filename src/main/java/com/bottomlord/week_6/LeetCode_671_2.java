package com.bottomlord.week_6;

import com.bottomlord.TreeNode;

public class LeetCode_671_2 {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }

        if (root.left == null && root.right == null) {
            return -1;
        }
        int one = root.val, left = root.left.val, right = root.right.val;

        if (one == left) {
            left = findSecondMinimumValue(root.left);
        }

        if (one == right) {
            right = findSecondMinimumValue(root.right);
        }

        if (left != -1 && right != -1) {
            return Math.min(left, right);
        }

        if (left != -1) {
            return left;
        }

        return right;
    }
}