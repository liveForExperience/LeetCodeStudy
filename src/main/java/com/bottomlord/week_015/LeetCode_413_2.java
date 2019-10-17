package com.bottomlord.week_015;

public class LeetCode_413_2 {
    public int numberOfArithmeticSlices(int[] A) {
        int count = 0, len = A.length;
        for (int start = 0; start < len - 2; start++) {
            int diff = A[start + 1] - A[start];
            for (int end = start + 2; end < len; end++) {
                if (A[end] - A[end - 1] == diff) {
                    count++;
                } else {
                    break;
                }
            }
        }

        return count;
    }
}