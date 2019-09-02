package com.bottomlord.week_005;

import com.bottomlord.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiveForExperience
 * @date 2019/8/5 22:19
 */
public class LeetCode_437_2 {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return dfs(root, sum, 0, map);
    }

    private int dfs(TreeNode node, int sum, int pathSum, Map<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }

        pathSum += node.val;
        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
        int count = map.getOrDefault(pathSum - sum, 0);
        int ans = count + dfs(node.left, sum, pathSum, map) + dfs(node.right, sum, pathSum, map);
        map.put(pathSum, map.get(pathSum) - 1);
        return ans;
    }
}
