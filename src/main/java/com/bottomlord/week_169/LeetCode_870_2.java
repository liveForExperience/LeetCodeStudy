package com.bottomlord.week_169;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chen yue
 * @date 2022-10-08 19:42:40
 */
public class LeetCode_870_2 {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Integer[] idx1 = new Integer[n], idx2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx1[i] = i;
            idx2[i] = i;
        }

        Arrays.sort(idx1, Comparator.comparingInt(x -> nums1[x]));
        Arrays.sort(idx2, Comparator.comparingInt(x -> nums2[x]));

        int[] ans = new int[n];
        int left = 0, right = n - 1;

        for (int i = 0; i < n; i++) {
            if (nums1[idx1[i]] > nums2[idx2[left]]) {
                ans[idx2[left]] = nums1[idx1[i]];
                left++;
            } else {
                ans[idx2[right]] = nums1[idx1[i]];
                right--;
            }
        }

        return ans;
    }
}