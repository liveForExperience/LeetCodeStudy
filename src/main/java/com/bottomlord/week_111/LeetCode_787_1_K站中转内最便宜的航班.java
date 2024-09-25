package com.bottomlord.week_111;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-08-24 08:16:13
 */
public class LeetCode_787_1_K站中转内最便宜的航班 {
    private int min = Integer.MAX_VALUE;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> mapping = new HashMap<>();
        for (int[] flight : flights) {
            mapping.computeIfAbsent(flight[0], x -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
        }

        dfs(src, dst, -1, k, 0, mapping);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private void dfs(int cur, int dst, int count, int k, int cost, Map<Integer, List<int[]>> mapping) {
        if (count > k) {
            return;
        }

        if (cur == dst) {
            min = Math.min(cost, min);
            return;
        }

        List<int[]> nexts = mapping.getOrDefault(cur, new ArrayList<>());

        for (int[] next : nexts) {
            dfs(next[0], dst, count + 1, k, cost + next[1], mapping);
        }
    }
}
