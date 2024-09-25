package com.bottomlord.week_030;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/1/29 13:40
 */
public class LeetCode_764_3 {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] dp = new int[N][N];
        for (int[] arr : dp) {
            Arrays.fill(arr, N);
        }

        for (int[] mine : mines) {
            dp[mine[0]][mine[1]] = 0;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0, k = N - 1, l = 0, r = 0, u = 0, d = 0; j < N; j++, k--) {
                dp[i][j] = Math.min(dp[i][j], l = dp[i][j] == 0 ? 0 : l + 1);
                dp[i][k] = Math.min(dp[i][k], r = dp[i][k] == 0 ? 0 : r + 1);
                dp[j][i] = Math.min(dp[j][i], u = dp[j][i] == 0 ? 0 : u + 1);
                dp[k][i] = Math.min(dp[k][i], d = dp[k][i] == 0 ? 0 : d + 1);
            }
        }

        int ans = 0;
        for (int[] arr : dp) {
            for (int num : arr) {
                ans = Math.max(ans, num);
            }
        }

        return ans;
    }
}