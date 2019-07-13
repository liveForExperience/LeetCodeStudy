package com.bottomlord.week_1;

import com.bottomlord.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/13 17:22
 */
public class LeetCode_590_2 {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();

        Node pre = null;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.peek();

            if (cur.children == null || (pre != null && cur.children.contains(pre))) {
                ans.add(cur.val);
                stack.pop();
                pre = cur;
            } else {
                for (int i = cur.children.size() - 1; i >= 0; i--) {
                    stack.push(cur.children.get(i));
                }
            }
        }

        return ans;
    }
}
