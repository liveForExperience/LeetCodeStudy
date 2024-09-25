package com.bottomlord.week_073;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/12/4 8:51
 */
public class LeetCode_372_2 {
    private final int base = 1337;

    public int superPow(int a, int[] b) {
        if (b.length == 0) {
            return 1;
        }

        int last = b[b.length - 1];
        int part1 = pow(a, last), part2 = pow(superPow(a, Arrays.copyOfRange(b, 0, b.length - 1)), 10);
        return (part1 * part2) % base;
    }

    private int pow(int a, int b) {
        if (b == 0) {
            return 1;
        }

        if (b % 2 == 1) {
            return (a * (pow(a, b - 1))) % base;
        } else {
            int c = pow(a, b / 2);
            return (c * c) % base;
        }
    }
}
