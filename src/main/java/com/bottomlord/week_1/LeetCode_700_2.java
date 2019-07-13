package com.bottomlord.week_1;

import com.bottomlord.TreeNode;

import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/13 19:17
 */
public class LeetCode_700_2 {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode ans = null;

        if (root == null) {
            return ans;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.val == val) {
                ans = node;
                break;
            }

            if (node.val < val) {
                stack.push(node.right);
            } else {
                stack.push(node.left);
            }
        }

        return ans;
    }
}
