package com.bottomlord.week_192;

/**
 * @author chen yue
 * @date 2023-03-15 17:48:18
 */
public class LeetCode_918_1_环形子数组的最大和 {
    public int maxSubarraySumCircular(int[] nums) {
        int sum, max, min, curMax, curMin;
        sum = max = min = curMin = curMax = nums[0];


        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            curMax = Math.max(curMax + nums[i], nums[i]);
            max = Math.max(max, curMax);

            curMin = Math.min(curMin + nums[i], nums[i]);
            min = Math.min(min, curMin);
        }

        return Math.max(sum - min, max);
    }
}
