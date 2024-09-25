package com.bottomlord.week_003;

/**
 * @author LiveForExperience
 * @date 2019/7/26 11:27
 */
public class LeetCode_496_2 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return new int[0];
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int num : nums2) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        max = Math.max(Math.abs(min), Math.abs(max));

        boolean hasNeg = false;
        int[] buckets;
        if (min < 0) {
            hasNeg = true;
            buckets = new int[max * 2 + 1];
        } else {
            buckets = new int[max + 1];
        }

        for (int i = 0; i < nums2.length; i++) {
            if (hasNeg) {
                buckets[nums2[i] + max] = i;
            } else {
                buckets[nums2[i]] = i;
            }
        }

        for (int i = 0; i < nums1.length; i++) {
            int index, num = nums1[i];
            if (hasNeg) {
                index = buckets[num + max];
            } else {
                index = buckets[num];
            }

            boolean find = false;
            for (int j = index; j < nums2.length; j++) {
                if (nums2[j] > num) {
                    nums1[i] = nums2[j];
                    find = true;
                    break;
                }
            }

            if (!find) {
                nums1[i] = -1;
            }
        }

        return nums1;
    }
}