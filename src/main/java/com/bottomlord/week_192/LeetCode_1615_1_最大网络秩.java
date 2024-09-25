package com.bottomlord.week_192;

/**
 * @author chen yue
 * @date 2023-03-15 08:55:20
 */
public class LeetCode_1615_1_最大网络秩 {
    public int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] g = new boolean[n][n];
        int[] inDegrees = new int[n];
        for (int[] road : roads) {
            int a = road[0], b = road[1];
            g[a][b] = true;
            g[b][a] = true;
            inDegrees[a]++;
            inDegrees[b]++;
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                max = Math.max(max, inDegrees[i] + inDegrees[j] - (g[i][j] ? 1 : 0));
            }
        }
        return max;
    }
}
