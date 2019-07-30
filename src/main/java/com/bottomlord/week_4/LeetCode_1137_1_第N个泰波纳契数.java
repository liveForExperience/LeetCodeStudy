package com.bottomlord.week_4;

/**
 * @author LiveForExperience
 * @date 2019/7/30 18:09
 */
public class LeetCode_1137_1_第N个泰波纳契数 {
    public int tribonacci(int n) {
        int[] memo = new int[n + 1];
        return rescurse(memo, n);
    }

    private int rescurse(int[] memo, int n) {
        if (n == 1 || n == 2) {
            return 1;
        }

        if (n == 0) {
            return 0;
        }

        if (memo[n] == 0) {
            memo[n] = rescurse(memo, n - 1) + rescurse(memo, n - 2) + rescurse(memo, n - 3);
        }

        return memo[n];
    }
}
