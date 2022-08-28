package com.bottomlord.week_163;

import com.bottomlord.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-08-27 11:55:44
 */
public class LeetCode_662_2 {
    public int widthOfBinaryTree(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        return dfs(new WrapNode(root, 0), 0, map);
    }

    private int dfs(WrapNode node, int depth, Map<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }

        if (!map.containsKey(depth)) {
            map.put(depth, node.index);
        }

        WrapNode left = node.node.left == null ? null : new WrapNode(node.node.left, node.index * 2),
                 right = node.node.right == null ? null : new WrapNode(node.node.right, node.index * 2 + 1);

        return Math.max(node.index - map.get(depth) + 1,
                Math.max(dfs(left, depth + 1, map), dfs(right, depth + 1, map)));
    }

    private static class WrapNode {
        private TreeNode node;
        private int index;

        public WrapNode(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
