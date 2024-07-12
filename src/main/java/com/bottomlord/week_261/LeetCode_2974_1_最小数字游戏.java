package com.bottomlord.week_261;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2024-07-12 18:18:08
 */
public class LeetCode_2974_1_最小数字游戏 {
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i += 2) {
            arr[i] = nums[i + 1];
            arr[i + 1] = nums[i];
        }
        return arr;
    }
}
