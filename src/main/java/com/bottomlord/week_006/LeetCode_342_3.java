package com.bottomlord.week_006;

public class LeetCode_342_3 {
    public boolean isPowerOfFour(int num) {
        if (num < 1) {
            return false;
        }

        return (num & (num - 1)) == 0 && num % 3 == 1;
    }
}
