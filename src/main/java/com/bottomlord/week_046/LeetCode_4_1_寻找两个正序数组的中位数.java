package com.bottomlord.week_046;

/**
 * @author ChenYue
 * @date 2020/5/24 19:57
 */
public class LeetCode_4_1_寻找两个正序数组的中位数 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mn = nums1.length + nums2.length;
        if (mn % 2 == 1) {
            return getElementK(nums1, nums2, mn / 2 + 1);
        } else {
            return (getElementK(nums1, nums2, mn / 2) + getElementK(nums1, nums2, mn / 2 + 1)) / 2.0;
        }
    }

    private double getElementK(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length, mi = 0, ni = 0;

        while (true) {
            if (mi >= m) {
                return nums2[ni + k - 1];
            }

            if (ni >= n) {
                return nums1[mi + k - 1];
            }

            if (k == 1) {
                return Math.min(nums1[mi], nums2[ni]);
            }

            int half = k / 2,
                tmi = Math.min(mi + half, m) - 1,
                tni = Math.min(ni + half, n) - 1;

            if (nums1[tmi] >= nums2[tni]) {
                k -= (tni - ni + 1);
                ni = tni + 1;
            } else {
                k -= (tmi - mi + 1);
                mi = tmi + 1;
            }
        }
    }
}
