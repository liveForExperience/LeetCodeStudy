package com.bottomlord.week_116;

import com.bottomlord.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-09-28 09:00:24
 */
public class LeetCode_437_2 {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return dfs(root, targetSum, 0, map);
    }

    private int dfs(TreeNode node, int sum, int curSum, Map<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }

        curSum += node.val;

        int count = map.getOrDefault(curSum - sum, 0);
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        count += dfs(node.left, sum, curSum, map) + dfs(node.right, sum, curSum, map);
        map.put(curSum, map.get(curSum) - 1);
        return count;
    }
}
