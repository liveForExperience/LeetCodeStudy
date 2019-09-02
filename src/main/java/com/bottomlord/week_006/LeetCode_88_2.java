package com.bottomlord.week_006;

import java.util.Arrays;

public class LeetCode_88_2 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0, index = 0;
        int[] nums1Copy = Arrays.copyOf(nums1, nums1.length);
        while (index < n + m) {
            if (p1 < m && p2 < n) {
                if (nums1Copy[p1] < nums2[p2]) {
                    nums1[index++] = nums1Copy[p1++];
                } else {
                    nums1[index++] = nums2[p2++];
                }
                continue;
            }

            if (p1 >= m) {
                System.arraycopy(nums2, p2, nums1, index, n - p2);
                index = n + m;
                continue;
            }

            System.arraycopy(nums1Copy, p1, nums1, index, m - p1);
            index = n + m;
        }
    }
}