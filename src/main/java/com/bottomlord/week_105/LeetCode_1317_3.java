package com.bottomlord.week_105;

public class LeetCode_1317_3 {
    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; a < n; a++) {
            int b = n - a;
            if (hasNoZero(a) && hasNoZero(b)) {
                return new int[]{a, b};
            }
        }
        return new int[0];
    }

    private boolean hasNoZero(int num) {
        while (num > 0) {
            if (num % 10 == 0) {
                return false;
            }

            num /= 10;
        }
        return true;
    }
}
