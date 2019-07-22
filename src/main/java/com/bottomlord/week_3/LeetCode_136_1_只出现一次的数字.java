package com.bottomlord.week_3;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/7/22 16:45
 */
public class LeetCode_136_1_只出现一次的数字 {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }

        return nums[nums.length - 1];
    }
}
