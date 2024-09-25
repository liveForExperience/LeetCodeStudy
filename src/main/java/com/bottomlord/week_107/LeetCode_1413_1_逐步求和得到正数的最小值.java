package com.bottomlord.week_107;

/**
 * @author ChenYue
 * @date 2021/7/30 9:34
 */
public class LeetCode_1413_1_逐步求和得到正数的最小值 {
    public int minStartValue(int[] nums) {
        int sum = 0, min = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            min = Math.min(sum, min);
        }

        return Math.max(1 - min, 1);
    }
}
