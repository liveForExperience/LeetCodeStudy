package com.bottomlord.week_024;

import com.bottomlord.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_865_1_具有所有最深节点的最小子树 {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(null, -1);
        dfs(root, null,map);

        int max = Integer.MIN_VALUE;
        for (int depth : map.values()) {
            max = Math.max(max, depth);
        }

        return answer(root, map, max);
    }

    private void dfs(TreeNode node, TreeNode parent, Map<TreeNode, Integer> map) {
        if (node == null) {
            return;
        }

        map.put(node, map.get(parent) + 1);
        dfs(node.left, node, map);
        dfs(node.right, node, map);
    }

    private TreeNode answer(TreeNode node, Map<TreeNode, Integer> map, int max) {
        if (node == null || map.get(node) == max) {
            return node;
        }

        TreeNode left = answer(node.left, map, max),
                 right = answer(node.right, map, max);

        if (left != null && right != null) {
            return node;
        }

        return left != null ? left : right;
    }
}
