package com.bottomlord.contest_20220626;

/**
 * @author chen yue
 * @date 2022-06-25 23:26:41
 */
public class Contest_2_1_统计放置房子的方法数 {
    public int countHousePlacements(int n) {
        int mod = 1000000007;
        long[][] dp = new long[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;

        for (int i = 1; i < n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
            dp[i][1] = dp[i - 1][0] % mod;
        }

        long side = (dp[n - 1][0] + dp[n - 1][1]) % mod;

        return (int) ((side * side) % mod);
    }
}
