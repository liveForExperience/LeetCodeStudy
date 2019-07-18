package com.bottomlord.week_2;

/**
 * @author LiveForExperience
 * @date 2019/7/18 13:04
 */
public class LeetCode_908_2 {
    public int smallestRangeI(int[] A, int K) {
        int min = A[0], max = min;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }

            if (A[i] < min) {
                min = A[i];
            }
        }

        int ans = max - min - 2 * Math.abs(K);
        return ans <= 0 ? 0 : ans;
    }
}
