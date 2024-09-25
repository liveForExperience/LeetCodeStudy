package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-28 10:44:03
 */
public class LeetCode_1480_1_一堆数组的动态和 {
    public int[] runningSum(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }

        int[] sum = new int[n];
        sum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return sum;
    }
}
