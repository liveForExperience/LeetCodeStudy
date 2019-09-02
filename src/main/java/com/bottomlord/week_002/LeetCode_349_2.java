package com.bottomlord.week_002;

/**
 * @author LiveForExperience
 * @date 2019/7/19 22:51
 */
public class LeetCode_349_2 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i : nums1) {
            max = Math.max(i, max);
            min = Math.min(i, min);
        }

        boolean[] bArr = new boolean[max - min + 1];
        for (int i = 0; i < nums1.length; i++) {
            bArr[nums1[i] - min] = true;
        }

        int[] arr = new int[nums2.length];
        int count = 0;
        for (int num : nums2) {
            if (num - min <= max - min && num - min >= 0 && bArr[num - min]) {
                arr[count++] = num;
                bArr[num - min] = false;
            }
        }

        int[] ans = new int[count];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = arr[i];
        }

        return ans;
    }
}