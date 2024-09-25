package com.bottomlord.week_034;

/**
 * @author ThinkPad
 * @date 2020/2/29 16:54
 */
public class Interview_42_1_连续子数组的最大和 {
    public int maxSubArray(int[] nums) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            if (sum > max) {
                max = sum;
            }

            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }
}
