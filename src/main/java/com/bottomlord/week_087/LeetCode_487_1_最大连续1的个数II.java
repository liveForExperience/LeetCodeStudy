package com.bottomlord.week_087;

/**
 * @author ChenYue
 * @date 2021/3/12 8:32
 */
public class LeetCode_487_1_最大连续1的个数II {
    public int findMaxConsecutiveOnes(int[] nums) {
        int j = -1, cur = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                cur++;
            }

            if (nums[i] == 0) {
                if (j == -1) {
                    j = i;
                    cur++;
                } else {
                    max = Math.max(max, cur);
                    cur = i - j;
                    j = i;
                }
            }
        }

        return Math.max(max, cur);
    }
}
