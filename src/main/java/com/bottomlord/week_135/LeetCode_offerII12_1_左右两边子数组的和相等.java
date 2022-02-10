package com.bottomlord.week_135;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-02-10 09:13:13
 */
public class LeetCode_offerII12_1_左右两边子数组的和相等 {
    public int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();

        if (sum - nums[0] == 0) {
            return 0;
        }

        int curSum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curSum += nums[i];
            if (sum - nums[i + 1] == curSum * 2) {
                return i + 1;
            }
        }

        if (sum - nums[nums.length - 1] == 0) {
            return nums.length - 1;
        }

        return -1;
    }
}
