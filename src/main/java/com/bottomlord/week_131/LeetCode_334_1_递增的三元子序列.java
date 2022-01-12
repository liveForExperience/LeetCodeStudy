package com.bottomlord.week_131;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-01-12 08:47:52
 */
public class LeetCode_334_1_递增的三元子序列 {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < n; i++) {
            int curMax = dp[i];
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    curMax = Math.max(curMax, dp[i] + 1);
                }
            }
            dp[i] = curMax;
            max = Math.max(curMax, max);
            if (max >= 3) {
                return true;
            }
        }
//[2,1,5,3,1,6]
        return false;
    }
}
