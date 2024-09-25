package com.bottomlord.week_006;

public class LeetCode_342_2 {
    public boolean isPowerOfFour(int num) {
        if (num < 1) {
            return false;
        }

        return (0x55555555 & num) == num && (num & (num - 1)) == 0;
    }
}