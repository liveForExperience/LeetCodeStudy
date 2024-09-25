package com.bottomlord.week_094;

/**
 * @author ChenYue
 * @date 2021/4/28 9:34
 */
public class LeetCode_633_3 {
    public boolean judgeSquareSum(int c) {
        for (int base = 2; base * base <= c; base++) {
            if (c % base != 0) {
                continue;
            }

            int exp = 0, cur = c;
            while (cur % base == 0) {
                cur /= base;
                exp++;
            }

            if (c % 4 == 3 && exp % 2 != 0) {
                return false;
            }
        }

        return c % 4 != 3;
    }
}
