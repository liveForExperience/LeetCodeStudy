package com.bottomlord.week_041;

/**
 * @author ChenYue
 * @date 2020/4/17 8:22
 */
public class LeetCode_55_2 {
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (max < i) {
                return false;
            }

            max = Math.max(max, i + nums[i]);
        }

        return true;
    }
}
