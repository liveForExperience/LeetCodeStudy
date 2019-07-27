package com.bottomlord.week_3;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/27 10:37
 */
public class LeetCode_257_2 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);

        Stack<String> strStack = new Stack<>();
        strStack.push("");

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            String str = strStack.pop();

            if (node == null) {
                continue;
            }

            if (node.left == null && node.right == null) {
                str += node.val;
                ans.add(str);
            }

            if (node.left != null) {
                nodeStack.push(node.left);
                strStack.push(str + node.val + "->");
            }

            if (node.right != null) {
                nodeStack.push(node.right);
                strStack.push(str + node.val + "->");
            }
        }

        return ans;
    }
}