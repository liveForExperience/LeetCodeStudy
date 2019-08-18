package com.bottomlord.week_6;

public class LeetCode_326_2 {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }

        while (n != 1) {
            if (n % 3 == 0) {
                n /= 3;
            } else {
                return false;
            }
        }

        return true;
    }
}