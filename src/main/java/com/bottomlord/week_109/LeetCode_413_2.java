package com.bottomlord.week_109;

/**
 * @author ChenYue
 * @date 2021/8/10 8:44
 */
public class LeetCode_413_2 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }

        int diff = nums[1] - nums[0], sum = 0;
        int[] dp = new int[n];

        dp[0] = dp[1] = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == diff) {
                dp[i] = dp[i - 1] + 1;
            } else {
                diff = nums[i] - nums[i - 1];
            }
            sum += dp[i];
        }

        return sum;
    }
}
