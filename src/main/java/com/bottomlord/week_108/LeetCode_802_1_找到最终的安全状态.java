package com.bottomlord.week_108;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/8/5 8:56
 */
public class LeetCode_802_1_找到最终的安全状态 {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<Integer> ans = new ArrayList<>();
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            if (safe(graph, color, i)) {
                ans.add(i);
            }
        }

        return ans;
    }

    private boolean safe(int[][] graph, int[] color, int x) {
        if (color[x] > 0) {
            return color[x] == 2;
        }

        color[x] = 1;
        for (int a : graph[x]) {
            if (!safe(graph, color, a)) {
                return false;
            }
        }

        color[x] = 2;
        return true;
    }
}
