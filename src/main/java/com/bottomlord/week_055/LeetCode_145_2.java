package com.bottomlord.week_055;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author ChenYue
 * @date 2020/7/21 8:24
 */
public class LeetCode_145_2 {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        Stack<TreeNode> stack = new Stack<>(), temp = new Stack<>();
        List<Integer> ans = new ArrayList<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }

            temp.push(node);
        }

        while (!temp.isEmpty()) {
            ans.add(temp.pop().val);
        }

        return ans;
    }
}