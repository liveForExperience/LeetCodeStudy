package com.bottomlord.week_041;

/**
 * @author ChenYue
 * @date 2020/4/16 12:55
 */
public class Interview_1617_1_连续数列 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                max = Math.max(sum += nums[j], max);
            }
        }
        return max;
    }
}
