package com.bottomlord.week_2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/7/19 22:27
 */
public class LeetCode_349_1_两个数组的交集 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }

        int len = nums1.length < nums2.length ? nums1.length : nums2.length;
        int[] arr = new int[len];
        int count = 0;
        for (int i : nums2) {
            if (set.contains(i) || set.isEmpty()) {
                arr[count++] = i;
                set.remove(i);
            }

            if (set.isEmpty()) {
                break;
            }
        }

        return Arrays.copyOf(arr, count);
    }
}
