package com.bottomlord.week_184;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-01-19 10:34:16
 */
public class LeetCode_940_2 {
    public int distinctSubseqII(String s) {
        int n = s.length(), mod = 1000000007;
        int[] last = new int[26], dp = new int[n];
        Arrays.fill(last, -1);
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                if (last[j] == -1) {
                    continue;
                }

                dp[i] = (dp[i] + dp[last[j]]) % mod;
            }

            last[s.charAt(i) - 'a'] = i;
        }

        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (last[i] == -1) {
                continue;
            }

            ans = (ans + dp[last[i]]) % mod;
        }

        return ans;
    }
}