package com.bottomlord.week_097;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2021/5/17 8:34
 */
public class LeetCode_993_2 {
    public boolean isCousins(TreeNode root, int x, int y) {
        return deep(root, x) == deep(root, y) && !isBrother(root, x, y);
    }

    private int deep(TreeNode node, int target) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }

        if (node.val == target) {
            return 0;
        }

        return Math.max(deep(node.left, target), deep(node.right, target)) + 1;
    }

    private boolean isBrother(TreeNode node, int x, int y) {
        if (node == null) {
            return false;
        }

        if (node.left != null && node.right != null) {
            if ((node.left.val == x && node.right.val == y) || (node.right.val == x && node.left.val == y)) {
                return true;
            }
        }

        return isBrother(node.left, x, y) || isBrother(node.right, x, y);
    }
}
