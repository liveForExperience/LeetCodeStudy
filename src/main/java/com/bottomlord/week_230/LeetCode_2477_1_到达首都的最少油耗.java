package com.bottomlord.week_230;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-12-05 19:53:43
 */
public class LeetCode_2477_1_到达首都的最少油耗 {
    public long minimumFuelCost(int[][] roads, int seats) {
        if (roads.length == 0) {
            return 0;
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] road : roads) {
            graph.computeIfAbsent(road[0], x -> new ArrayList<>()).add(road[1]);
            graph.computeIfAbsent(road[1], x -> new ArrayList<>()).add(road[0]);
        }

        long[] r = dfs(0, graph, seats, new boolean[roads.length + 1]);
        return r[0];
    }

    private long[] dfs(int node, Map<Integer, List<Integer>> graph, int seats, boolean[] memo) {
        if (!graph.containsKey(node)) {
            return new long[]{1, 1};
        }

        memo[node] = true;
        List<Integer> nexts = graph.get(node);
        long sumCost = 0, sumPerson = 1;
        for (Integer next : nexts) {
            if (memo[next]) {
                continue;
            }

            long[] r = dfs(next, graph, seats, memo);
            sumPerson += r[1];
            sumCost += r[0];
        }

        long curCost = 0;
        if (node != 0) {
            curCost = sumPerson / seats + (sumPerson % seats == 0 ? 0 : 1);
        }

        return new long[]{sumCost + curCost, sumPerson};
    }
}
