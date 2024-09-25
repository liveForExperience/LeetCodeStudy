package com.bottomlord.week_153;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-06-15 23:15:18
 */
public class LeetCode_719_2 {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, l = 0, r = nums[n - 1] - nums[0];

        while (l <= r) {
            int mid = l + (r - l) / 2, count = 0;
            for (int end = 0, start = 0; end < n; end++) {
                while (nums[end] - nums[start] > mid) {
                     start++;
                }

                count += end - start;
            }

            if (count >= k) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}