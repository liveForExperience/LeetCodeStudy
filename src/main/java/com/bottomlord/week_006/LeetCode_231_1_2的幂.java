package com.bottomlord.week_006;

public class LeetCode_231_1_2的幂 {
    public boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        }

        while (n != 1) {
            double num = n / 2.0;
            n /= 2;
            if (n != num) {
                return false;
            }
        }

        return true;
    }
}
