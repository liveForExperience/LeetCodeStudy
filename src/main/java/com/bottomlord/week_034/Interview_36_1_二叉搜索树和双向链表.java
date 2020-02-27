package com.bottomlord.week_034;

import com.bottomlord.TreeNode;

/**
 * @author ThinkPad
 * @date 2020/2/27 8:26
 */
public class Interview_36_1_二叉搜索树和双向链表 {
    private TreeNode smallest;
    private TreeNode biggest;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }

        dfs(root);
        smallest.left = biggest;
        biggest.right = smallest;
        return smallest;
    }

    private void dfs(TreeNode node) {
        if (node != null) {
            dfs(node.left);

            if (biggest != null) {
                biggest.right = node;
                node.left = biggest;
            } else {
                smallest = node;
            }
            biggest = node;

            dfs(node.right);
        }
    }
}
