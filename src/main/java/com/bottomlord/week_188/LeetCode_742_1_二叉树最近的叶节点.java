package com.bottomlord.week_188;

import com.bottomlord.TreeNode;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-02-16 10:56:19
 */
public class LeetCode_742_1_二叉树最近的叶节点 {
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        dfs(graph, root, null);

        Queue<TreeNode> queue = new ArrayDeque<>();
        Set<TreeNode> seen = new HashSet<>();
        for (TreeNode treeNode : graph.keySet()) {
            if (treeNode != null && treeNode.val == k) {
                queue.offer(treeNode);
            }
        }

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                continue;
            }

            List<TreeNode> nodes = graph.getOrDefault(node, new ArrayList<>());

            if (nodes.size() <= 1) {
                return node.val;
            } else {
                for (TreeNode treeNode : nodes) {
                    if (seen.contains(treeNode) || treeNode == null) {
                        continue;
                    }

                    queue.offer(treeNode);
                    seen.add(node);
                }
            }
        }

        return -1;
    }

    private void dfs(Map<TreeNode, List<TreeNode>> graph, TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }

        graph.computeIfAbsent(node, x -> new ArrayList<>()).add(parent);
        graph.computeIfAbsent(parent, x -> new ArrayList<>()).add(node);

        dfs(graph, node.left, node);
        dfs(graph, node.right, node);
    }
}
