package com.bottomlord.week_186;

/**
 * @author chen yue
 * @date 2023-01-31 20:15:47
 */
public class LeetCode_2540_1_最小公共值 {
    public int getCommon(int[] nums1, int[] nums2) {
        int i1 = 0, i2 = 0, n1 = nums1.length, n2 = nums2.length;
        while (i1 < n1 && i2 < n2) {
            if (nums1[i1] == nums2[i2]) {
                return nums1[i1];
            } else if (nums1[i1] < nums2[i2]) {
                i1++;
            } else {
                i2++;
            }
        }

        return -1;
    }
}
