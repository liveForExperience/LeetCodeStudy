package com.bottomlord.week_094;

/**
 * @author ChenYue
 * @date 2021/4/29 11:28
 */
public class LeetCode_552_1_学生出勤记录II {
    public int checkRecord(int n) {
        long mod = 1000000007;
        long[] dp = new long[n <= 5 ? 6 : n + 1];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        dp[3] = 7;
        for (int i = 4; i <= n; i++) {
            dp[i] = ((2 * dp[i - 1]) % mod + (mod - dp[i - 4])) % mod;
        }
        long sum = dp[n];

        for (int i = 1; i <= n; i++) {
            sum += (dp[i - 1] * dp[n - i]) % mod;
        }

        return (int) (sum % mod);
    }
}
