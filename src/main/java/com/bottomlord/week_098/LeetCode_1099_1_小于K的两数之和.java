package com.bottomlord.week_098;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/5/27 8:52
 */
public class LeetCode_1099_1_小于K的两数之和 {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = -1, head = 0, tail = nums.length - 1;
        while (head < tail) {
            if (nums[head] + nums[tail] >= k) {
                tail--;
            } else {
                sum = nums[head++] + nums[tail];
            }
        }
        return sum;
    }
}
