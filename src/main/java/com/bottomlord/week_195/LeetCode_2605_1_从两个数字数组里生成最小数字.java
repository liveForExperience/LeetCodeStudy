package com.bottomlord.week_195;

/**
 * @author chen yue
 * @date 2023-04-05 14:08:49
 */
public class LeetCode_2605_1_从两个数字数组里生成最小数字 {
    public int minNumber(int[] nums1, int[] nums2) {
        int a = 10;
        for (int num : nums1) {
            a = Math.min(num, a);
        }

        int b = 10;
        for (int num : nums2) {
            b = Math.min(num, b);
        }

        return Math.min(a, b) * 10 + Math.max(a, b);
    }
}
