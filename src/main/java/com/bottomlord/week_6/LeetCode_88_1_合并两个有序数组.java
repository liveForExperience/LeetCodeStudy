package com.bottomlord.week_6;

import java.util.Arrays;

public class LeetCode_88_1_合并两个有序数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }
}
