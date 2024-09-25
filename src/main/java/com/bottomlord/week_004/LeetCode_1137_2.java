package com.bottomlord.week_004;

/**
 * @author LiveForExperience
 * @date 2019/7/30 18:23
 */
public class LeetCode_1137_2 {
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        int n0 = 0, n1 = 1, n2 = 1;
        for (int i = 3; i <= n; i++) {
            int tmp0 = n1, tmp1 = n2;
            n2 = n0 + n1 + n2;
            n0 = tmp0;
            n1 = tmp1;
        }

        return n2;
    }
}