package com.bottomlord.week_008;

import com.bottomlord.TreeNode;

public class LeetCode_687_3 {
    private int count = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfsOut(root);
        return count;
    }

    private void dfsOut(TreeNode node) {
        if (node == null) {
            return;
        }

        count = Math.max(count, dfsIn(node.left, node.val) + dfsIn(node.right, node.val));

        dfsOut(node.left);
        dfsOut(node.right);
    }

    private int dfsIn(TreeNode node, int pre) {
        if (node == null) {
            return 0;
        }

        if (node.val != pre) {
            return 0;
        }

        return Math.max(dfsIn(node.left, pre) + 1, dfsIn(node.right, pre) + 1);
    }
}
