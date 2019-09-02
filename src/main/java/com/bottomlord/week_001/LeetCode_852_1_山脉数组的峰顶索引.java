package com.bottomlord.week_001;

/**
 * @author LiveForExperience
 * @date 2019/7/14 13:31
 */
public class LeetCode_852_1_山脉数组的峰顶索引 {
    public int peakIndexInMountainArray(int[] A) {
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i-1] && A[i] > A[i+1]) {
                return i;
            }
        }

        return 0;
    }
}
