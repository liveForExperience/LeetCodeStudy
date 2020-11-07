package com.bottomlord.week_069;

/**
 * @author ChenYue
 * @date 2020/11/7 10:19
 */
public class LeetCode_327_1_区间的个数 {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        long[] sums = new long[len];
        long sum = 0;
        for (int i = 0; i < len; i++) {
            sums[i] = (sum += nums[i]);
        }

        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                long num = sums[j] - sums[i] + nums[i];
                count += (num >= lower && num <= upper) ? 1 : 0;
            }
        }
        return count;
    }
}
