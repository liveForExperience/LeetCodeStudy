package com.bottomlord.week_107;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2021/7/27 9:04
 */
public class LeetCode_671_2 {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }

        if (root.left == null && root.right == null) {
            return -1;
        }

        int val = root.val, left = root.left.val, right = root.right.val;

        if (left == val) {
            left = findSecondMinimumValue(root.left);
        }

        if (right == val) {
            right = findSecondMinimumValue(root.right);
        }

        if (left != -1 && right != -1) {
            return Math.min(left, right);
        }

        if (left == -1) {
            return right;
        }

        return left;
    }
}
