package com.bottomlord.week_001;

import com.bottomlord.TreeNode;

import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/9 20:31
 */
public class LeetCode_617_2 {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }

        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{t1, t2});
        while (!stack.empty()) {
            TreeNode[] nodes = stack.pop();
            if (nodes[1] == null) {
                continue;
            }

            nodes[0].val += nodes[1].val;

            if (nodes[0].left == null) {
                nodes[0].left = nodes[1].left;
            } else {
                stack.push(new TreeNode[]{nodes[0].left, nodes[1].left});
            }

            if (nodes[0].right == null) {
                nodes[0].right = nodes[1].right;
            } else {
                stack.push(new TreeNode[]{nodes[0].right, nodes[1].right});
            }
        }

        return t1;
    }
}
