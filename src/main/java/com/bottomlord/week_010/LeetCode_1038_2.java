package com.bottomlord.week_010;

import com.bottomlord.TreeNode;

public class LeetCode_1038_2 {
    public TreeNode bstToGst(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    private int dfs(TreeNode node, int add) {
        if (node == null) {
            return add;
        }

        int right = dfs(node.right, add);
        node.val += right;
        return dfs(node.left, node.val);
    }
}