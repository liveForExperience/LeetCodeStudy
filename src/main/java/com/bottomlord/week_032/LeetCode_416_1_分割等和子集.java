package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/10 15:39
 */
public class LeetCode_416_1_分割等和子集 {
    public boolean canPartition(int[] nums) {
        int len = nums.length, sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[][] dp = new boolean[len][target + 1];
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                if (j == nums[i]) {
                    dp[i][j] = true;
                    continue;
                }

                if (nums[i] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[len - 1][target];
    }
}