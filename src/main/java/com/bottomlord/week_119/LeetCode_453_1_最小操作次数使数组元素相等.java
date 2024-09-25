package com.bottomlord.week_119;

/**
 * @author chen yue
 * @date 2021-10-20 09:05:24
 */
public class LeetCode_453_1_最小操作次数使数组元素相等 {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(num, min);
        }

        int sum = 0;
        for (int num : nums) {
            sum += num - min;
        }

        return sum;
    }
}
