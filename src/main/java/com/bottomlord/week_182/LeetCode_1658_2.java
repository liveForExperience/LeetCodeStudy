package com.bottomlord.week_182;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-01-07 05:52:59
 */
public class LeetCode_1658_2 {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (x > sum) {
            return -1;
        }

        if (x == sum) {
            return n;
        }

        int l = -1, r = 0, lSum = 0, rSum = sum, ans = Integer.MAX_VALUE;
        for (; l < n; l++) {
            if (l != -1) {
                lSum += nums[l];
            }

            while (r < n && lSum + rSum > x) {
                rSum -= nums[r++];
            }

            if (lSum + rSum == x) {
                ans = Math.min(ans, l + 1 + (n - r));
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}