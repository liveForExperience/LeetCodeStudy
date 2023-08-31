package com.bottomlord.week_216;

/**
 * @author chen yue
 * @date 2023-08-31 18:20:24
 */
public class LeetCode_1761_1_一个图中连通三元组的最小度数 {
    public int minTrioDegree(int n, int[][] edges) {
        int[][] g = new int[n + 1][n + 1];
        int[] degrees = new int[n + 1];
        for (int[] edge : edges) {
            int x = Math.min(edge[0], edge[1]), y = Math.max(edge[0], edge[1]);
            g[x][y] = 1;
            degrees[x]++;
            degrees[y]++;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (g[i][j] == 0) {
                    continue;
                }

                for (int k = j + 1; k <= n; k++) {
                    if (g[i][k] == 0 || g[j][k] == 0) {
                        continue;
                    }

                    ans = Math.min(ans, degrees[i] + degrees[j] + degrees[k] - 6);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
