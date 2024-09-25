package com.bottomlord.week_015;

public class LeetCode_413_1_等差数列划分 {
    public int numberOfArithmeticSlices(int[] A) {
        int count = 0, len = A.length;
        for (int start = 0; start < len - 2; start++) {
            int diff = A[start + 1] - A[start];
            for (int end = start + 2; end < len; end++) {
                int index = start + 1;
                for (; index <= end; index++) {
                    if (A[index + 1] - A[index] != diff) {
                        break;
                    }
                }

                if (index > end) {
                    count++;
                }
            }
        }
        return count;
    }
}
