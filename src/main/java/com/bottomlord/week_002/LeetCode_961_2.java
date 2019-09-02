package com.bottomlord.week_002;

/**
 * @author LiveForExperience
 * @date 2019/7/17 13:06
 */
public class LeetCode_961_2 {
    public int repeatedNTimes(int[] A) {
        for (int i = 2; i < A.length; i++) {
            if (A[i] == A[i - 1] || A[i] == A[i - 2]) {
                return A[i];
            }

            if (A[i - 1] == A[i - 2]) {
                return A[i - 1];
            }
        }

        return A[0];
    }
}