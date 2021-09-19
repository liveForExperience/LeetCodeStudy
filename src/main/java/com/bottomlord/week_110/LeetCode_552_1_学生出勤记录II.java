package com.bottomlord.week_110;

/**
 * @author chen yue
 * @date 2021-08-18 08:19:12
 */
public class LeetCode_552_1_学生出勤记录II {
    public int checkRecord(int n) {
        int mod = 1000000007;
        long[][][] dp = new long[n + 1][3][2];
        dp[0][0][0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i][0][0] = (dp[i][0][0] + dp[i - 1][0][0]) % mod;
            dp[i][0][0] = (dp[i][0][0] + dp[i - 1][1][0]) % mod;
            dp[i][0][0] = (dp[i][0][0] + dp[i - 1][2][0]) % mod;
            dp[i][0][1] = (dp[i][0][1] + dp[i - 1][0][1]) % mod;
            dp[i][0][1] = (dp[i][0][1] + dp[i - 1][1][1]) % mod;
            dp[i][0][1] = (dp[i][0][1] + dp[i - 1][2][1]) % mod;

            dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][0]) % mod;
            dp[i][2][0] = (dp[i][2][0] + dp[i - 1][1][0]) % mod;
            dp[i][1][1] = (dp[i][1][1] + dp[i - 1][0][1]) % mod;
            dp[i][2][1] = (dp[i][2][1] + dp[i - 1][1][1]) % mod;

            dp[i][0][1] = (dp[i][0][1] + dp[i - 1][0][0]) % mod;
            dp[i][0][1] = (dp[i][1][1] + dp[i - 1][1][0]) % mod;
            dp[i][0][1] = (dp[i][2][1] + dp[i - 1][2][0]) % mod;
        }

        return (int) ((dp[n][0][0] +
                dp[n][1][0] +
                dp[n][2][0] +
                dp[n][0][1] +
                dp[n][1][1] +
                dp[n][2][1]) % mod);
    }
}
