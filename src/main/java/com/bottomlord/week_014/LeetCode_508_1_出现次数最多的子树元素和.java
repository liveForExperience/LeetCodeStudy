package com.bottomlord.week_014;

import com.bottomlord.TreeNode;

import java.util.*;

public class LeetCode_508_1_出现次数最多的子树元素和 {
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);

        if (map.size() == 0) {
            return new int[0];
        }

        int max = Collections.max(map.values());
        List<Integer> list = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == max) {
                list.add(entry.getKey());
            }
        }

        int[] ans = new int[list.size()];
        int index = 0;
        for (int num : list) {
            ans[index++] = num;
        }
        return ans;
    }

    private int dfs(TreeNode node, Map<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left, map);
        int right = dfs(node.right, map);

        int sum = left + right + node.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);

        return sum;
    }
}
