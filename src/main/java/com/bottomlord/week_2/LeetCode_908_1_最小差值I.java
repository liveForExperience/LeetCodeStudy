package com.bottomlord.week_2;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/7/18 12:28
 */
public class LeetCode_908_1_最小差值I {
    public int smallestRangeI(int[] A, int K) {
        Arrays.sort(A);
        int min = A[A.length - 1] - A[0] - 2 * Math.abs(K);
        return min <= 0 ? 0 : min;
    }
}
