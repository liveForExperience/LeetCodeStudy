package com.bottomlord.week_073;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/12/4 8:25
 */
public class LeetCode_372_1_超级次方 {
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
        a %= base;
        int ans = 1;
        for (int i = 0; i < b; i++) {
            ans *= a;
            ans %= base;
        }

        return ans;
    }
}
