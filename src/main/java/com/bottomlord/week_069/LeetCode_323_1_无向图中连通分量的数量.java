package com.bottomlord.week_069;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/11/3 8:25
 */
public class LeetCode_323_1_无向图中连通分量的数量 {
    public int countComponents(int n, int[][] edges) {
        boolean[] memo = new boolean[n];
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!memo[i]) {
                count++;
                dfs(graph, memo, i);
            }
        }

        return count;
    }

    private void dfs(List<List<Integer>> graph, boolean[] memo, int index) {
        if (memo[index]) {
            return;
        }

        memo[index] = true;

        for (int i = 0; i < graph.get(index).size(); i++) {
            if (!memo[graph.get(index).get(i)]) {
                dfs(graph, memo, graph.get(index).get(i));
            }
        }
    }
}
