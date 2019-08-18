package com.bottomlord.week_6;

public class LeetCode_326_1_3的幂 {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }

        while (n != 1) {
            double dNum = n / 3.0;
            n /= 3;
            if (dNum != n) {
                return false;
            }
        }

        return true;
    }
}
