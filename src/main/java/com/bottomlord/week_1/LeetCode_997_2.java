package com.bottomlord.week_1;

/**
 * @author LiveForExperience
 * @date 2019/7/11 12:55
 */
public class LeetCode_997_2 {
    public int[] sortedSquares(int[] A) {
        int i = 0;
        while (i < A.length && A[i] < 0) {
            i++;
        }
        int j = i + 1;

        int[] ans = new int[A.length];
        int t = 0;

        while (i >= 0 && j < A.length) {
            if (A[i] * A[i] < A[j] * A[j]) {
                ans[t++] = A[i] * A[i];
                i--;
            } else {
                ans[t++] = A[j] * A[j];
                j++;
            }
        }

        while (i >= 0) {
            ans[t++] = A[i] * A[i];
            i--;
        }

        while (j < A.length) {
            ans[t++] = A[j] * A[j];
            j++;
        }

        return ans;
    }
}