package com.bottomlord.week_083;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2021/2/10 17:15
 */
public class LeetCode_450_1_删除二叉搜索树中的节点 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            }

            if (root.right != null) {
                root.val = processNode(root.right).val;
                deleteNode(root.right, root.val);
            }

            root.val = preNode(root.left).val;
            deleteNode(root.left, root.val);
        }

        return root;
    }

    private TreeNode processNode(TreeNode node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private TreeNode preNode(TreeNode node) {
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
}
