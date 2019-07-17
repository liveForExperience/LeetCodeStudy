package com.bottomlord.week_2;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/7/17 12:56
 */
public class LeetCode_961_1_重复N次的元素 {
    public int repeatedNTimes(int[] A) {
        Arrays.sort(A);
        int n = A.length / 2;
        if (A[n - 1] == A[n]) {
            return A[n];
        }

        if (A[n - 1] == A[n - 2]) {
            return A[n - 1];
        }

        return A[n];
    }
}
