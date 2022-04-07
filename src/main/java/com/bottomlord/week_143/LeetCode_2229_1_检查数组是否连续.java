package com.bottomlord.week_143;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-04-07 21:56:11
 */
public class LeetCode_2229_1_检查数组是否连续 {
    public boolean isConsecutive(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 != nums[i + 1]) {
                return false;
            }
        }

        return true;
    }
}
