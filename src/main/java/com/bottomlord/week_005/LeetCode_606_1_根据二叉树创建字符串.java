package com.bottomlord.week_005;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class LeetCode_606_1_根据二叉树创建字符串 {
    public String tree2str(TreeNode t) {
        Queue<String> queue = new ArrayDeque<>();
        dfs(t, queue);

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }

        return sb.toString();
    }

    private void dfs(TreeNode node, Queue<String> stack) {
        if (node == null) {
            return;
        }

        stack.offer("" + node.val);

        if (node.left == null && node.right == null) {
            return;
        }

        stack.offer("(");
        dfs(node.left, stack);
        stack.offer(")");

        if (node.right != null) {
            stack.offer("(");
            dfs(node.right, stack);
            stack.offer(")");
        }
    }
}
