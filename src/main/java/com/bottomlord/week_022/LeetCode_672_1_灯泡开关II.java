package com.bottomlord.week_022;

public class LeetCode_672_1_灯泡开关II {
    public int flipLights(int n, int m) {
        if (n == 0 || m == 0) {
            return 1;
        }

        if (n == 1) {
            return 2;
        }

        if (n == 2) {
            if (m == 1) {
                return 3;
            }

            if (m == 2) {
                return 4;
            }

            if (m > 2) {
                return 4;
            }
        }

        if (n >= 3) {
            if (m == 1) {
                return 4;
            }

            if (m == 2) {
                return 7;
            }

            if (m > 2) {
                return 8;
            }
        }

        return 8;
    }
}
