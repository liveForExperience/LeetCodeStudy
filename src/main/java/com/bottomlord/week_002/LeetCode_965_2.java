package com.bottomlord.week_002;

import com.bottomlord.TreeNode;

import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/18 13:20
 */
public class LeetCode_965_2 {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return false;
        }

        Stack<TreeNode> stack = new Stack<>();
        int val = root.val;
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val != val) {
                return false;
            }

            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return true;
    }
}
