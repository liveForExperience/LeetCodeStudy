package com.bottomlord.contest_20200419;

/**
 * @author ChenYue
 * @date 2020/4/20 8:56
 */
public class Contest_4_1_生成数组 {
    public int numOfArrays(int n, int m, int k) {
        int mod = 1000000007;
        long[][][] dp = new long[n][m + 1][k + 2];
        for (int j = 1; j <= m; j++) {
            dp[0][j][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int l = 1; l <= k; l++) {
                    for (int ij = 1; ij <= m; ij++) {
                        int ik = l + (ij > j ? 1 : 0);
                        dp[i][Math.max(ij, j)][ik] += dp[i - 1][j][l];
                        dp[i][Math.max(ij, j)][ik] %= mod;
                    }
                }
            }
        }

        long ans = 0;
        for (int i = 1; i <= m; i++) {
            ans += dp[n - 1][i][k];
            ans %= mod;
        }
        return (int)ans;
    }
}
