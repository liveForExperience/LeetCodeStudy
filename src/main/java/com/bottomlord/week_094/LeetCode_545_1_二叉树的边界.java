package com.bottomlord.week_094;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/4/26 11:11
 */
public class LeetCode_545_1_二叉树的边界 {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        ans.add(root.val);
        findLeft(root.left, ans);
        if (root.left != null || root.right != null) {
            findLeaf(root, ans);
        }
        findRight(root.right, ans);

        return ans;
    }

    private void findLeft(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            return;
        }

        list.add(node.val);

        findLeft(node.left, list);

        if (node.left == null) {
            findLeft(node.right, list);
        }
    }

    private void findLeaf(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            list.add(node.val);
        }

        findLeaf(node.left, list);
        findLeaf(node.right, list);
    }

    private void findRight(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            return;
        }

        if (node.right == null) {
            findRight(node.left, list);
        }

        findRight(node.right, list);

        list.add(node.val);
    }
}
