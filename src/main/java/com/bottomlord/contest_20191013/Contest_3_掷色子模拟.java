package com.bottomlord.contest_20191013;

public class Contest_3_掷色子模拟 {
    public int dieSimulator(int n, int[] rollMax) {
        int[][][] dp = new int[n][6][16];
        int mod = 1000000007;

        for (int i = 0; i < 6; i++) {
            dp[0][i][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    if (j != k) {
                        for (int l = 1; l <= rollMax[k]; l++) {
                            dp[i][j][1] += dp[i - 1][k][l];
                            dp[i][j][1] %= mod;
                        }
                    } else {
                        for (int l = 1; l < rollMax[k]; l++) {
                            dp[i][j][l + 1] += dp[i - 1][k][l];
                            dp[i][j][l + 1] %= mod;
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j <= rollMax[i]; j++) {
                ans += dp[n - 1][i][j];
                ans &= mod;
            }
        }

        return ans;
    }
}