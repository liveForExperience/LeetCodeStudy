package com.bottomlord.week_269;

/**
 * @author chen yue
 * @date 2024-09-03 17:17:51
 */
public class LeetCode_2708_2 {
    public long maxStrength(int[] nums) {
        long max = nums[0], min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            long curMax = max, curMin = min;
            min = Math.min(Math.min(min, num), Math.min(curMin * num, curMax * num));
            max = Math.max(Math.max(max, num), Math.max(curMin * num, curMax * num));
        }

        return max;
    }
}
