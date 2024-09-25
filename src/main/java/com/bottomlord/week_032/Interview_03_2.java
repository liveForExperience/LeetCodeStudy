package com.bottomlord.week_032;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/2/13 19:17
 */
public class Interview_03_2 {
    public int findRepeatNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }

        return 1;
    }
}
