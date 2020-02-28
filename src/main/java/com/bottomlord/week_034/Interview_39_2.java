package com.bottomlord.week_034;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/2/28 12:17
 */
public class Interview_39_2 {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int target = nums.length / 2;
        for (int i = 0; i < target + 1; i++) {
            if (nums[i] == nums[i + target]) {
                return nums[i];
            }
        }

        return -1;
    }
}