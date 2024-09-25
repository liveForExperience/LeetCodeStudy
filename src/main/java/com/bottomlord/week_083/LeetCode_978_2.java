package com.bottomlord.week_083;

/**
 * @author ChenYue
 * @date 2021/2/8 10:10
 */
public class LeetCode_978_2 {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][2];
        dp[0][0] = dp[0][1] = 1;

        int ans = 1;
        for (int i = 1; i < len; i++) {
            dp[i][0] = dp[i][1] = 1;
            if (arr[i - 1] < arr[i]) {
                dp[i][0] = dp[i - 1][1] + 1;
            } else if (arr[i - 1] > arr[i]) {
                dp[i][1] = dp[i - 1][0] + 1;
            }
            ans = Math.max(ans, dp[i][0]);
            ans = Math.max(ans, dp[i][1]);
        }

        return ans;
    }
}
