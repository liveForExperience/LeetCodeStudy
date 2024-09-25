package com.bottomlord.week_132;

/**
 * @author chen yue
 * @date 2022-01-17 08:45:42
 */
public class LeetCode_1220_1_统计元音字母序列的数目 {
    public int countVowelPermutation(int n) {
        long[] dp = new long[5];
        for (int i = 0; i < 5; i++) {
            dp[i] = 1;
        }

        int mod = 1000000007;

        for (int j = 0; j < n; j++) {
            long a = dp[0], e = dp[1], i = dp[2], o = dp[3], u = dp[4];

            dp[0] = (e + i + u) % mod;
            dp[1] = (a + i) % mod;
            dp[2] = (e + o) % mod;
            dp[3] = i % mod;
            dp[4] = (i + o) % mod;
        }

        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans = (ans + dp[i]) % mod;
        }
        return (int)ans;
    }
}
