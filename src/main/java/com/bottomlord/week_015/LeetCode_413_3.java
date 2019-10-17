package com.bottomlord.week_015;

public class LeetCode_413_3 {
    public int numberOfArithmeticSlices(int[] A) {
        int ans = 0, len = A.length;
        int[] dp = new int[len];
        for (int i = 2; i < len; i++) {
            dp[i] = A[i] - A[i - 1] == A[i - 1] - A[i - 2] ? dp[i - 1] + 1 : 0;
            ans += dp[i];
        }
        return ans;
    }
}