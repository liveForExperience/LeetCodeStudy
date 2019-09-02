package com.bottomlord.week_005;

public class LeetCode_896_1_单调数列 {
    public boolean isMonotonic(int[] A) {
        boolean up = false, down = false;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) {
                down = true;
                break;
            }

            if (A[i] < A[i + 1]) {
                up = true;
                break;
            }
        }

        for (int i = 1; i < A.length - 1; i++) {
            if (up && A[i] > A[i + 1]) {
                return false;
            }

            if (down && A[i] < A[i + 1]) {
                return false;
            }
        }

        return true;
    }
}
