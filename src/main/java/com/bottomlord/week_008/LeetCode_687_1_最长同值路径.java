package com.bottomlord.week_008;

import com.bottomlord.TreeNode;

public class LeetCode_687_1_最长同值路径 {
    private int count = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfsOut(root);
        return count;
    }

    private void dfsOut(TreeNode node) {
        if (node == null) {
            return;
        }

        count = Math.max(count, dfsIn(node.left, node.val, 0) + dfsIn(node.right, node.val, 0));

        dfsOut(node.left);
        dfsOut(node.right);
    }

    private int dfsIn(TreeNode node, int pre, int sum) {
        if (node == null) {
            return sum;
        }

        if (node.val == pre) {
            sum++;
        } else {
            return sum;
        }

        return Math.max(dfsIn(node.left, pre, sum), dfsIn(node.right, pre, sum));
    }
}
