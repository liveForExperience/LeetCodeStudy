package com.bottomlord.week_121;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-11-06 14:20:50
 */
public class LeetCode_268_1_丢失的数字 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        return n * n / 2 - Arrays.stream(nums).sum();
    }
}
