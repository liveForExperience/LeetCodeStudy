package com.bottomlord.week_151;

/**
 * @author chen yue
 * @date 2022-06-02 23:24:32
 */
public class LeetCode_1855_1_下标对中的最大距离 {
    public int maxDistance(int[] nums1, int[] nums2) {
        int ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            int index = binarySearch(nums2, nums1[i], i, nums2.length - 1);

            if (index == -1) {
                continue;
            }

            ans = Math.max(index - i, ans);
        }

        return ans;
    }

    private int binarySearch(int[] arr, int target, int l, int r) {
        while (l <= r) {
            int mid = (r - l) / 2 + l;

            int num = arr[mid];

            if (num >= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        if (l - 1 >= arr.length || l - 1 < 0) {
            return -1;
        }

        if (arr[l - 1] < target) {
            return -1;
        }

        return l - 1;
    }
}
