package com.bottomlord.week_031;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/2/3 18:18
 */
public class LeetCode_688_1_马在棋盘上的概率 {
    public double knightProbability(int N, int K, int r, int c) {
        int[][] paths = new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        Map<Integer, Map<Integer, Map<Integer, Double>>> memo = new HashMap<>();
        return dfs(N, K, r, c, memo, paths);
    }

    private double dfs(int n, int k, int r, int c, Map<Integer, Map<Integer, Map<Integer, Double>>> memo, int[][] paths) {
        if (!isValid(n, r, c)) {
            return 0.0;
        }

        if (k == 0) {
            return 1.0;
        }

        if (memo.containsKey(k) && memo.get(k).containsKey(r) && memo.get(k).get(r).containsKey(c)) {
            return memo.get(k).get(r).get(c);
        }

        double p = 0;
        for (int[] path : paths) {
            p += dfs(n, k, r + path[0], c + path[1], memo, paths);
        }

        p /= 8;
        Map<Integer, Map<Integer, Double>> rmap = memo.getOrDefault(k, new HashMap<>());
        Map<Integer, Double> cmap = rmap.getOrDefault(r, new HashMap<>());
        cmap.put(c, p);
        rmap.put(r, cmap);
        memo.put(k, rmap);

        return p;
    }

    private boolean isValid(int n, int r, int c) {
        return r < n && r >= 0 && c < n && c >= 0;
    }
}
