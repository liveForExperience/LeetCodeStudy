package com.bottomlord.week_108;

/**
 * @author chen yue
 * @date 2021-08-08 13:58:50
 */
public class LeetCode_1137_1_第N个泰波那契数列 {
    public int tribonacci(int n) {
        if (n <= 1) {
            return n;
        }

        if (n == 2) {
            return 1;
        }

        int a1 = 0, a2 = 1, a3 = 1;
        for (int i = 3; i<= n; i++) {
            int a4= a1 + a2 + a3;
            a1 = a2;
            a2 = a3;
            a3 = a4;
        }

        return a3;
    }
}
