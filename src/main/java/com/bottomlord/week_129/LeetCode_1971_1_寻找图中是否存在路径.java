package com.bottomlord.week_129;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-01-02 20:33:20
 */
public class LeetCode_1971_1_寻找图中是否存在路径 {
    public boolean validPath(int n, int[][] edges, int start, int end) {
        Map<Integer, List<Integer>> mapping = new HashMap<>();
        for (int[] edge : edges) {
            mapping.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
            mapping.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
        }

        return dfs(start, end, mapping, new boolean[n]);
    }

    private boolean dfs(int start, int end, Map<Integer, List<Integer>> mapping, boolean[] memo) {
        if (memo[start]) {
            return false;
        }

        memo[start] = true;

        if (start == end) {
            return true;
        }

        if (!mapping.containsKey(start)) {
            return false;
        }

        for (Integer next : mapping.get(start)) {
            boolean result = dfs(next, end, mapping, memo);
            if (result) {
                return true;
            }
        }

        return false;
    }
}
