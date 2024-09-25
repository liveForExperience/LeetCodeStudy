package com.bottomlord.week_151;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-06-01 14:10:16
 */
public class LeetCode_350_1_两个数组的交集II {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int n1 = nums1.length, n2 = nums2.length, i2 = 0;
        for (int i = 0; i < n1; i++) {
            int num = nums1[i];
            int index = binarySearch(nums2, num, i2, n2 - 1);
            if (index != -1) {
                i2 = index + 1;
                list.add(nums2[index]);
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private int binarySearch(int[] arr, int target, int l, int r) {
        while (l <= r) {
            int mid = (l + r) >> 1;
            int num = arr[mid];

            if (num < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        if (l >= arr.length) {
            return -1;
        }

        return arr[l] == target ? l : -1;
    }
}
