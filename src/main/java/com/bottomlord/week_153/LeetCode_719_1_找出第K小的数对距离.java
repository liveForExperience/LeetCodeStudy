package com.bottomlord.week_153;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-06-15 22:52:59
 */
public class LeetCode_719_1_找出第K小的数对距离 {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0, r = nums[n - 1] - nums[0];

        while (l <= r) {
            int mid = (r - l) / 2 + l;

            int count = 0;
            for (int i = 0; i < n; i++) {
                int index = binarySearch(nums, i, nums[i] - mid);
                count += i - index;
            }

            if (count >= k) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private int binarySearch(int[] nums, int end, int target) {
        int l = 0, r = end;

        while (l < r) {
            int mid = l + (r - l) / 2;

            int num = nums[mid];
            if (num >= mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}
