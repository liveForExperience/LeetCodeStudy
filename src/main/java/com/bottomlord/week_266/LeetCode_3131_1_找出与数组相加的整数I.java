package com.bottomlord.week_266;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2024-08-08 11:47:42
 */
public class LeetCode_3131_1_找出与数组相加的整数I {
    public int addedInteger(int[] nums1, int[] nums2) {
        return (Arrays.stream(nums2).sum() - Arrays.stream(nums1).sum()) / nums1.length;
    }
}
