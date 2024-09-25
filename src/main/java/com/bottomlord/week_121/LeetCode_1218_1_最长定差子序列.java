package com.bottomlord.week_121;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-11-05 09:04:21
 */
public class LeetCode_1218_1_最长定差子序列 {
    public int longestSubsequence(int[] arr, int difference) {
        int[] dp = new int[40001];
        int ans = 1;
        for (int num : arr) {
            dp[num + 20000] = dp[num - difference + 20000] + 1;
            ans = Math.max(ans, dp[num + 20000]);
        }

        return ans;
    }
}
