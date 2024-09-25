package com.bottomlord.week_170;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-10-16 13:04:56
 */
public class LeetCode_886_1_可能的二分法 {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] dislike : dislikes) {
            graph.computeIfAbsent(dislike[0], x -> new ArrayList<>()).add(dislike[1]);
            graph.computeIfAbsent(dislike[1], x -> new ArrayList<>()).add(dislike[0]);
        }

        int[] colors = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (colors[i] != 0) {
                continue;
            }

            if (!dfs(i, 1, graph, colors)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int index, int color, Map<Integer, List<Integer>> graph, int[] colors) {
        colors[index] = color;

        for (Integer other : graph.getOrDefault(index, new ArrayList<>())) {
            if (colors[other] == color) {
                return false;
            }

            if (colors[other] == 0 && !dfs(other, 3 - color, graph, colors)) {
                return false;
            }
        }

        return true;
    }
}
