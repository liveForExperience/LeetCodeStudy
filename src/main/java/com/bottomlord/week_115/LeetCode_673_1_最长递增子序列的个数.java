package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-20 23:04:53
 */
public class LeetCode_673_1_最长递增子序列的个数 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, max = 1, ans = 0;
        int[] dp = new int[n], count = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = 0; j < n; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] < dp[j] + 1) {
                        count[i] = count[j];
                        dp[i] = dp[j] + 1;
                    } else if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }

            if (max < dp[i]) {
                max = dp[i];
                ans = count[i];
            } else if (max == dp[i]) {
                ans += count[i];
            }
        }

        return ans;
    }
}
