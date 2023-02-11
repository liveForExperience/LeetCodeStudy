package com.bottomlord.week_187;

/**
 * @author chen yue
 * @date 2023-02-10 08:46:05
 */
public class LeetCode_1223_1_掷骰子模拟 {
    private int mod = 1000000007;

    public int dieSimulator(int n, int[] rollMax) {
        int[][] dp = new int[n + 1][6];
        for (int i = 0; i < 6; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % mod;
                }

                int index = i - rollMax[j] - 1;
                if (index > 0) {
                    for (int k = 0; k < 6; k++) {
                        if (k == j) {
                            continue;
                        }

                        dp[i][j] = (dp[i][j] - dp[index][k] + mod) % mod;
                    }
                } else if (index == 0) {
                    dp[i][j]--;
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < 6; i++) {
            ans = (ans + dp[n][i]) % mod;
        }
        return (int) ans;
    }
}
