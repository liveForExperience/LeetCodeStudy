package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/16 12:44
 */
public class LeetCode_154_1_寻找旋转排序数组中的最小值II {
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        if (nums[0] < nums[nums.length - 1]) {
            return nums[0];
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return nums[i];
            }
        }

        return nums[0];
    }
}
