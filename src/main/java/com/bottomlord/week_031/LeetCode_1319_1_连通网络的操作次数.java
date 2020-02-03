package com.bottomlord.week_031;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/2/3 11:03
 */
public class LeetCode_1319_1_连通网络的操作次数 {
    public int makeConnected(int n, int[][] connections) {
        if (n - 1 > connections.length) {
            return -1;
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] connection : connections) {
            graph.get(connection[0]).add(connection[1]);
            graph.get(connection[1]).add(connection[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, visited, graph);
                count++;
            }
        }

        return count - 1;
    }

    private void dfs(int i, boolean[] visited, List<List<Integer>> graph) {
        visited[i] = true;
        for (int n : graph.get(i)) {
            if (!visited[n]) {
                dfs(n, visited, graph);
            }
        }
    }
}
