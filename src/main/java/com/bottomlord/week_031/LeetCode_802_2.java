package com.bottomlord.week_031;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/2/3 16:30
 */
public class LeetCode_802_2 {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int len = graph.length;
        List<Integer> ans = new ArrayList<>();
        int[] color = new int[len];

        for (int i = 0; i < len; i++) {
           if (dfs(i, graph, color)) {
               ans.add(i);
           }
        }

        return ans;
    }

    private boolean dfs(int node, int[][] graph, int[] color) {
        if (color[node] > 0) {
            return color[node] == 2;
        }

        color[node] = 1;
        for (int n : graph[node]) {
            if (color[n] == 2) {
                continue;
            }

            if (color[n] == 1 || !dfs(n, graph, color)) {
                return false;
            }
        }

        color[node] = 2;
        return true;
    }
}
