package com.bottomlord.week_199;

/**
 * @author chen yue
 * @date 2023-05-03 13:44:01
 */
public class LeetCode_2656_1_K个元素的最大和 {
    public int maximizeSum(int[] nums, int k) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }

        return (max * 2 + k - 1) * k / 2;
    }
}
