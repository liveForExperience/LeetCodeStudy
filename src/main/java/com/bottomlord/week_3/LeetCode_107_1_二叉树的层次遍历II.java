package com.bottomlord.week_3;

import com.bottomlord.TreeNode;

import java.util.*;

/**
 * @author LiveForExperience
 * @date 2019/7/23 15:41
 */
public class LeetCode_107_1_二叉树的层次遍历II {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Stack<List<Integer>> stack = new Stack<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();

            List<Integer> list = new ArrayList<>(count);
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
                list.add(node.val);
            }

            stack.push(list);
        }

        while (!stack.isEmpty()) {
            ans.add(stack.pop());
        }

        return ans;
    }
}
