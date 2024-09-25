package com.bottomlord.week_044;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/5/9 8:57
 */
public class Interview_1712_BiNode {
    private TreeNode pointer;
    public TreeNode convertBiNode(TreeNode root) {
        pointer = new TreeNode(0);
        TreeNode start = pointer;

        dfs(root);
        return start.right;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.left);

        node.left = null;
        pointer.right = node;
        pointer = node;

        dfs(node.right);
    }
}
