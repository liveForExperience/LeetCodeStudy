package com.bottomlord.week_092;

/**
 * @author ChenYue
 * @date 2021/4/16 11:37
 */
public class LeetCode_523_1_连续的子数组和 {
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] sums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((sums[j + 1] - sums[i]) % k == 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
