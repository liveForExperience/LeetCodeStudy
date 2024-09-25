package com.bottomlord.week_013;

import com.bottomlord.TreeNode;

public class LeetCode_222_1_完全二叉树的节点个数 {
    private int num = 0;

    public int countNodes(TreeNode root) {
        dfs(root);
        return num;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        num++;
        dfs(node.left);
        dfs(node.right);
    }
}
