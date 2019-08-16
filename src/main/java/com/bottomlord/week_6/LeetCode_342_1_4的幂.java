package com.bottomlord.week_6;

public class LeetCode_342_1_4的幂 {
    public boolean isPowerOfFour(int num) {
        if (num < 1) {
            return false;
        }

        while (num != 1 ) {
            double doubleNum = num / 4.0;
            num /= 4;
            if (num != doubleNum) {
                return false;
            }
        }

        return true;
    }
}
