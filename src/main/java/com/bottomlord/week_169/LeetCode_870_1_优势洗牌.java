package com.bottomlord.week_169;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-10-08 13:13:12
 */
public class LeetCode_870_1_优势洗牌 {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums2.length; i++) {
            int[] arr = Arrays.copyOfRange(nums1, i, nums1.length);
            Arrays.sort(arr);
            System.arraycopy(arr, 0, nums1, i, nums1.length - i);

            int target = nums2[i],
                index = binarySearch(nums1, i, target);

            if (nums1[index] <= target) {
                continue;
            }

            swap(nums1, i, index);
        }

        return nums1;
    }

    private int binarySearch(int[] nums, int start, int target) {
        int l = start, r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2,
                num = nums[mid];

            if (num <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
