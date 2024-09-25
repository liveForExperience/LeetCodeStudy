package com.bottomlord.week_106;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/7/20 8:16
 */
public class LeetCode_1877_1_数组中最大数对和的最小值 {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int head = 0, tail = nums.length - 1, max = Integer.MIN_VALUE;
        while (head != tail) {
            max = Math.max(nums[head++] + nums[tail--], max);
        }

        return max;
    }
}
