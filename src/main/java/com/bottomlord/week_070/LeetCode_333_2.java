package com.bottomlord.week_070;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/11/13 8:45
 */
public class LeetCode_333_2 {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Result result = find(root);
        return result == null ? 0 : result.size;
    }

    private Result find(TreeNode node) {
        if (node == null) {
            return null;
        }

        Result left = null, right = null;
        if (node.left != null) {
            left = find(node.left);
        }

        if (node.right != null) {
            right = find(node.right);
        }

        boolean leftIsValid = node.left == null || (left.root == node.left && left.max < node.val),
                rightIsValid = node.right == null || (right.root == node.right && right.min > node.val);

        if (leftIsValid && rightIsValid) {
            Result result = new Result();
            result.root = node;
            result.min = left == null ? node.val : left.min;
            result.max = right == null ? node.val : right.max;
            result.size = (left == null ? 0 : left.size) + (right == null ? 0 : right.size) + 1;
            return result;
        }

        if (left != null && right != null) {
            return left.size > right.size ? left : right;
        }

        if (left != null) {
            return left;
        }

        return right;
    }

    class Result {
        private TreeNode root;
        private int min;
        private int max;
        private int size;
    }
}
