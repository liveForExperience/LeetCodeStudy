package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-26 16:19:08
 */
public class LeetCode_371_1_两整数之和 {
    public int getSum(int a, int b) {
        int xor = a ^ b, and = a & b, tmp;

        while (and != 0) {
            and <<= 1;
            tmp = xor;
            xor ^= and;
            and &= tmp;
        }

        return xor;
    }
}
