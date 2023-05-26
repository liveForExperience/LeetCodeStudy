package com.bottomlord.week_202;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-05-24 22:38:19
 */
public class LeetCode_1377_1_T秒后青蛙的位置 {
    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0] - 1].add(edge[1] - 1);
            graph[edge[1] - 1].add(edge[0] - 1);
        }

        boolean[] memo = new boolean[n];

        return dfs(0, t, graph, memo, target);
    }

    private double dfs(int index, int t, List<Integer>[] graph, boolean[] memo, int target) {
        int count = graph[index].size();
        if (t == 0 || (count -= (index == 0 ? 0 : 1)) == 0) {
            return index == target - 1 ? 1 : 0;
        }

        memo[index] = true;
        double result = 0;
        for (Integer nextIndex : graph[index]) {
            if (memo[nextIndex]) {
                continue;
            }

            result += dfs(nextIndex, t - 1, graph, memo, target);
        }

        return result / count;
    }
}
