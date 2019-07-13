package com.bottomlord.week_1;

import com.bottomlord.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/13 18:18
 */
public class LeetCode_589_2 {
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            ans.add(node.val);

            if (node.children != null) {
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    stack.push(node.children.get(i));
                }
            }
        }

        return ans;
    }
}
