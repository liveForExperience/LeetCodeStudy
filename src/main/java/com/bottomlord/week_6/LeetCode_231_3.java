package com.bottomlord.week_6;

public class LeetCode_231_3 {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        while (n != 1) {
            if (n % 2 == 0) {
                n  /= 2;
            } else {
                return false;
            }
        }

        return true;
    }
}