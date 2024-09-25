package com.bottomlord.week_055;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/7/23 9:08
 */
public class LeetCode_156_1_上下翻转二叉树 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }

        TreeNode ans = dfs(root.left, root);

        root.left = null;
        root.right = null;

        return ans;
    }

    private TreeNode dfs(TreeNode node, TreeNode root) {
        TreeNode cur;
        if (node.left == null) {
            cur = node;
        } else {
            cur = dfs(node.left, node);
        }

        node.left = root.right;
        node.right = root;

        return cur;
    }
}
