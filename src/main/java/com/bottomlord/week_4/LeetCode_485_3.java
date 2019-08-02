package com.bottomlord.week_4;

/**
 * @author LiveForExperience
 * @date 2019/8/2 22:18
 */
public class LeetCode_485_3 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;

        for (int num : nums) {
            if (num == 1) {
                count++;
            }

            if (num == 0) {
                max = Math.max(max, count);
                count = 0;
            }
        }

        if (count != 0) {
            max = Math.max(max, count);
        }

        return max;
    }
}