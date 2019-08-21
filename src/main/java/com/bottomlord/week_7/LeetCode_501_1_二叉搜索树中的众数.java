package com.bottomlord.week_7;

import com.bottomlord.TreeNode;

import java.util.*;

public class LeetCode_501_1_二叉搜索树中的众数 {
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);
        int max = Collections.max(map.values());
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == max) {
                list.add(entry.getKey());
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public void dfs(TreeNode node, Map<Integer, Integer> map) {
        if (node == null) {
            return;
        }

        map.put(node.val, map.getOrDefault(node.val, 0) + 1);
        dfs(node.left, map);
        dfs(node.right, map);
    }
}
