package com.bottomlord.week_105;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/7/14 8:18
 */
public class LeetCode_1818_1_绝对差值和 {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int[] sorted = Arrays.copyOfRange(nums1, 0, nums1.length);
        Arrays.sort(sorted);
        int n = nums1.length, sum = 0, maxDiff = 0, mod = 1000000007;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + diff) % mod;

            int index = binarySearch(sorted, nums2[i]);
            if (index < n) {
                maxDiff = Math.max(maxDiff, diff - (sorted[index] - nums2[i]));
            }

            if (index > 0) {
                maxDiff = Math.max(maxDiff, diff - (nums2[i] - sorted[index - 1]));
            }
        }

        return (sum - maxDiff + mod) % mod;
    }

    private int binarySearch(int[] arr, int target) {
        int head = 0, tail = arr.length - 1;
        if (arr[tail] < target) {
            return tail + 1;
        }

        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (arr[mid] < target) {
                head = mid + 1;
            } else if (arr[mid] > target){
                tail = mid;
            } else {
                return mid;
            }
        }

        return head;
    }
}
