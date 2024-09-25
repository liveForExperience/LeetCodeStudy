package com.bottomlord.week_024;

import com.bottomlord.TreeNode;

public class LeetCode_865_2 {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    private DfsResult dfs(TreeNode node) {
        if (node == null) {
            return new DfsResult(null, 0);
        }

        DfsResult left = dfs(node.left),
                  right = dfs(node.right);

        if (left.dist > right.dist) {
            return new DfsResult(left.node, left.dist + 1);
        }

        if (right.dist > left.dist) {
            return new DfsResult(right.node, right.dist + 1);
        }

        return new DfsResult(node, left.dist + 1);
    }
}