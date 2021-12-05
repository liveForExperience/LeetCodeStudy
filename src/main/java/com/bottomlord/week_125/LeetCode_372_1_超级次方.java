package com.bottomlord.week_125;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-12-05 20:48:36
 */
public class LeetCode_372_1_超级次方 {
    private int base = 1337;
    public int superPow(int a, int[] b) {
        if (b.length == 0) {
            return 0;
        }

        int last = b[b.length - 1];
        return pow(a, last) * pow(superPow(a, Arrays.copyOfRange(b, 0, b.length - 1)), 10) % base;
    }

    private int pow(int a, int b) {
        if (b == 0) {
            return 1;
        }

        if (b % 2 == 1) {
            return (a * pow(a, b - 1)) % base;
        } else {
            int c = pow(a, b / 2);
            return c * c % base;
        }
    }
}
