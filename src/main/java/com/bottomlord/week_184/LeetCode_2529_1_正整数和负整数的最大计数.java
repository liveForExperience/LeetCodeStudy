package com.bottomlord.week_184;

/**
 * @author chen yue
 * @date 2023-01-19 09:49:19
 */
public class LeetCode_2529_1_正整数和负整数的最大计数 {
    public int maximumCount(int[] nums) {
        int positive = 0, negative = 0;
        for (int num : nums) {
            if (num < 0) {
                negative++;
            }

            if (num > 0) {
                positive++;
            }
        }

        return Math.max(positive, negative);
    }
}
