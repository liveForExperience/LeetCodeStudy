package com.bottomlord.week_262;

/**
 * @author chen yue
 * @date 2024-07-16 13:48:11
 */
public class LeetCode_2956_1_找到两个数组中的公共元素 {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        boolean[] memo1 = new boolean[101], memo2 = new boolean[101];
        for (int num : nums1) {
            memo1[num] = true;
        }

        for (int num : nums2) {
            memo2[num] = true;
        }

        int cnt1 = 0, cnt2 = 0;
        for (int num : nums1) {
            if (memo2[num]) {
                cnt1++;
            }
        }

        for (int num : nums2) {
            if (memo1[num]) {
                cnt2++;
            }
        }

        return new int[]{cnt1, cnt2};
    }
}
