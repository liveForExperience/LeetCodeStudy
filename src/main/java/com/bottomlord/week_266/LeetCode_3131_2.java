package com.bottomlord.week_266;

/**
 * @author chen yue
 * @date 2024-08-08 13:41:27
 */
public class LeetCode_3131_2 {
    public int addedInteger(int[] nums1, int[] nums2) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int i = 0; i < nums1.length; i++) {
            min1 = Math.min(min1, nums1[i]);
            min2 = Math.min(min2, nums2[i]);
        }

        return min2 - min1;
    }
}
