package com.bottomlord.week_7;

import java.util.Arrays;

public class LeetCode_350_2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int len1 = nums1.length, len2 = nums2.length;
        int[] arr = new int[Math.max(len1, len2)];

        int i1 = 0, i2 = 0, i = 0;
        while (i1 < len1 && i2 < len2) {
            if (nums1[i1] < nums2[i2]) {
                i1++;
                continue;
            }

            if (nums1[i1] > nums2[i2]) {
                i2++;
                continue;
            }

            arr[i++] = nums1[i1];
            i1++;
            i2++;
        }

        return Arrays.copyOf(arr, i);
    }
}
