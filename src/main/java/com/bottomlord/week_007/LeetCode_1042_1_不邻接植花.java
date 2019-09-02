package com.bottomlord.week_007;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode_1042_1_不邻接植花 {
    public int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(i, new HashSet<>());
        }

        for (int[] graph : paths) {
            map.get(graph[0]).add(graph[1] - 1);
        }

        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            boolean[] used = new boolean[5];
            for (int j: map.get(i)) {
                if (ans[j] != 0) {
                    used[ans[j]] = true;
                }
            }

            for (int j = 1; j < 5; j++) {
                if (!used[j]) {
                    ans[i] = j;
                    break;
                }
            }
        }

        return ans;
    }
}
