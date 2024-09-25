package com.bottomlord.week_005;

public class LeetCode_896_2 {
    public boolean isMonotonic(int[] A) {
        int len = A.length;
        int i = 0, j = 0;

        while (i + 1 < len && A[i] <= A[i + 1]) {
            i++;
        }

        if (i == len - 1) {
            return true;
        }

        while (j + 1 < len && A[j] >= A[j + 1]) {
            j++;
        }

        if (j == len - 1) {
            return true;
        }

        return false;
    }
}