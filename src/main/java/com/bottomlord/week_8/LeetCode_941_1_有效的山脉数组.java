package com.bottomlord.week_8;

public class LeetCode_941_1_有效的山脉数组 {
    public boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }
        if (A[1] <= A[0]) {
            return false;
        }

        boolean up = true;
        for (int i = 2; i < A.length; i++) {
            if (A[i] == A[i - 1]) {
                return false;
            }

            if (A[i] > A[i - 1]) {
                if (!up) {
                    return false;
                }
            } else {
                if (up) {
                    up = false;
                }
            }
        }

        return !up;
    }
}
