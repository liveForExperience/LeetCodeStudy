package com.bottomlord.week_174;

/**
 * @author chen yue
 * @date 2022-11-12 22:53:49
 */
public class LeetCode_790_1_多米诺和托米诺平铺 {
    public int numTilings(int n) {
        long[] dp = {1, 0, 0, 0};
        int mod = (int) 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            long[] g = new long[4];
            g[0] = (dp[0] + dp[1] + dp[2] + dp[3]) % mod;
            g[1] = (dp[2] + dp[3]) % mod;
            g[2] = (dp[1] + dp[3]) % mod;
            g[3] = dp[0];
            dp = g;
        }
        return (int) dp[0];
    }
}
