package com.bottomlord.week_1;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/7/14 13:41
 */
public class LeetCode_852_2 {
    public int peakIndexInMountainArray(int[] A) {
        int left = 0, right = A.length - 1, mid = (right - left) / 2 + left;
        while (left != right) {
            if (A[mid] > A[mid] - 1) {
                left = mid;
            } else {
                right = mid;
            }

            mid = (right - left) / 2 + left;
        }

        return mid;
    }
}
