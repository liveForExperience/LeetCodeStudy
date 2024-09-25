package com.bottomlord.week_013;

import com.bottomlord.TreeNode;

public class LeetCode_1123_1_最深叶节点的最近公共祖先 {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }

        int left = depth(root.left);
        int right = depth(root.right);

        if (left == right) {
            return root;
        } else if (left > right) {
            return lcaDeepestLeaves(root.left);
        } else {
            return lcaDeepestLeaves(root.right);
        }
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(depth(node.left) + 1, depth(node.right) + 1);
    }
}
