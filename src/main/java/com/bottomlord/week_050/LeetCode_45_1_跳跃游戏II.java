package com.bottomlord.week_050;

/**
 * @author ChenYue
 * @date 2020/6/17 8:34
 */
public class LeetCode_45_1_跳跃游戏II {
    public int jump(int[] nums) {
        return recurse(nums, 0, 0);
    }

    private int recurse(int[] nums, int index, int count) {
        if (index == nums.length - 1) {
            return count;
        }

        if (index >= nums.length - 1) {
            return Integer.MAX_VALUE;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= nums[index]; i++) {
            min = Math.min(min, recurse(nums, index + i, count + 1));
        }

        return min;
    }
}
