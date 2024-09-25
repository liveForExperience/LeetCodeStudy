package com.bottomlord.week_050;

/**
 * @author ChenYue
 * @date 2020/6/17 12:57
 */
public class LeetCode_45_3 {
    public int jump(int[] nums) {
        int max = 0, end = 0, count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, nums[i] + i);
            if (i == end) {
                end = max;
                count++;
            }
        }

        return count;
    }
}
