package com.bottomlord.week_006;

public class LeetCode_263_1_丑数 {
    public boolean isUgly(int num) {
        if (num < 2) {
            return false;
        }

        while (num != 1) {
            if (num % 2 == 0) {
                num /= 2;
                continue;
            }

            if (num % 3 == 0) {
                num /= 3;
                continue;
            }

            if (num % 5 == 0) {
                num /= 5;
                continue;
            }

            return false;
        }

        return true;
    }
}
