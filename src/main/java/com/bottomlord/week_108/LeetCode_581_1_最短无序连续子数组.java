package com.bottomlord.week_108;

/**
 * @author ChenYue
 * @date 2021/8/3 8:17
 */
public class LeetCode_581_1_最短无序连续子数组 {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length, end = -1, max = nums[0], start = -1, min = nums[n - 1];
        for (int i = 1; i < n; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                end = i;
            }
        }

        if (end == -1) {
            return 0;
        }

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= min) {
                min = nums[i];
            } else {
                start = i;
            }
        }

        return end - start + 1;
    }
}
