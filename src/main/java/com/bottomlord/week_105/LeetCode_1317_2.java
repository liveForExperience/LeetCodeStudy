package com.bottomlord.week_105;

public class LeetCode_1317_2 {
    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; a < n; a++) {
            int b = n - a;
            if (!Integer.toString(a).contains("0") &&
                    !Integer.toString(b).contains("0")) {
                return new int[]{a, b};
            }
        }

        return new int[0];
    }
}
