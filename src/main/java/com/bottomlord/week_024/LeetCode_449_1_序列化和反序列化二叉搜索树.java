package com.bottomlord.week_024;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;

public class LeetCode_449_1_序列化和反序列化二叉搜索树 {
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString().trim();
    }

    private void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        dfs(node.left, sb);
        dfs(node.right, sb);

        sb.append(" ").append(node.val);
    }

    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (String num : data.split(" ")) {
            queue.offer(Integer.valueOf(num));
        }

        return getTree(Integer.MIN_VALUE, Integer.MAX_VALUE, queue);
    }

    private TreeNode getTree(Integer low, Integer high, ArrayDeque<Integer> queue) {
        if (queue.isEmpty()) {
            return null;
        }

        int val = queue.getLast();

        if (val < low || val > high) {
            return null;
        }

        queue.removeLast();

        TreeNode node = new TreeNode(val);
        node.right = getTree(val, high, queue);
        node.left = getTree(low, val, queue);

        return node;
    }
}
