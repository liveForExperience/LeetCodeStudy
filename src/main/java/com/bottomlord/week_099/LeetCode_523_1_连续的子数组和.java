package com.bottomlord.week_099;

/**
 * @author ChenYue
 * @date 2021/6/2 8:13
 */
public class LeetCode_523_1_连续的子数组和 {
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] sums = new int[len + 1];
        for (int i = 1; i < len + 1; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < len + 1; i++) {
            for (int j = i + 2; j < len + 1; j++) {
                if ((sums[j] - sums[i]) % k == 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
