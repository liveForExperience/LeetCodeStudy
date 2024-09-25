package com.bottomlord.week_004;

/**
 * @author LiveForExperience
 * @date 2019/7/31 18:32
 */
public class LeetCode_1009_1_十进制整数的反码 {
    public int bitwiseComplement(int N) {
        if (N == 0) {
            return 1;
        }

        int x = 32, n = N;
        while (n > 0) {
            x--;
            n >>= 1;
        }

        return ~(N << x) >> x;
    }
}
