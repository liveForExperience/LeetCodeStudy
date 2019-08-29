package com.bottomlord.week_8;

import com.bottomlord.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_687_2 {
    private int count = 0;
    public int longestUnivaluePath(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        dfsOut(root, map);
        return count;
    }

    private void dfsOut(TreeNode node, Map<TreeNode, Integer> map) {
        if (node == null) {
            return;
        }

        count = Math.max(count, dfsIn(node.left, node.val, map) + dfsIn(node.right, node.val, map));

        dfsOut(node.left, map);
        dfsOut(node.right, map);
    }

    private int dfsIn(TreeNode node, int pre, Map<TreeNode, Integer> map) {
        if (node == null) {
            return 0;
        }

        if (map.containsKey(node)) {
            return map.get(node);
        }

        if (node.val != pre) {
            return 0;
        }

        int val = Math.max(dfsIn(node.left, pre, map) + 1, dfsIn(node.right, pre, map) + 1);
        map.put(node, val);
        return val;
    }
}