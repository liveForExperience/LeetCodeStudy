package com.bottomlord.week_099;

/**
 * @author ChenYue
 * @date 2021/5/31 8:18
 */
public class LeetCode_342_1_4的幂 {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }

        int bit = 1, time = 0;
        while (time < 16) {
            if (bit == n) {
                return true;
            }

            if (bit > n) {
                return false;
            }

            bit <<= 2;
            time++;
        }

        return false;
    }
}
