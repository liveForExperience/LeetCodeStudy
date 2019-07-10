package com.bottomlord.week_1;

import com.bottomlord.TreeNode;

import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/10 13:22
 */
public class LeetCode_226_2 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return root;
    }
}