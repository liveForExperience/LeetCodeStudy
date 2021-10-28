package com.bottomlord.week_120;

/**
 * @author chen yue
 * @date 2021-10-28 08:41:03
 */
public class LeetCode_231_1_2的幂 {
    public boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }

        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                return false;
            }
        }

        return true;
    }
}
