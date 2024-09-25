package com.bottomlord.week_110;

/**
 * @author chen yue
 * @date 2021-08-16 08:45:39
 */
public class LeetCode_1470_1_重新排列数组 {
    public int[] shuffle(int[] nums, int n) {
        int[] arr = new int[nums.length];
        int i1 = 0, i2 = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (i < n) {
                arr[i1++ * 2] = nums[i];
            } else {
                arr[i2++ * 2 + 1] = nums[i];
            }
        }

        return arr;
    }
}
