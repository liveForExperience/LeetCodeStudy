package com.bottomlord.week_191;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-03-12 14:26:36
 */
public class LeetCode_1245_2 {
    private Map<Integer, List<Integer>> graph;
    private int[] dp1, dp2;
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        dp1 = new int[n];
        dp2 = new int[n];
        graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
        }

        dfs(0, -1);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp1[i] + dp2[i]);
        }
        return ans;
    }

    private void dfs(int cur, int pre) {
        for (Integer next : graph.getOrDefault(cur, new ArrayList<>())) {
            if (next == pre) {
                continue;
            }
            dfs(next, cur);
            int dis = dp1[next] + 1;

            if (dis >= dp1[cur]) {
                dp2[cur] = dp1[cur];
                dp1[cur] = dis;
            } else if (dis > dp2[cur]) {
                dp2[cur] = dis;
            }
        }
    }
}