package com.bottomlord.week_257;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2024-06-15 14:27:09
 */
public class LeetCode_2779_1_数组的最小美丽值 {
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int cursor = 0, max = 1, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (n - i + 1 <= max) {
                break;
            }

            int num = nums[i];
            while (cursor < n && nums[cursor] <= num + 2 * k) {
                cursor++;
            }

            max = Math.max(max, cursor - i);
        }

        return max;
    }
}
