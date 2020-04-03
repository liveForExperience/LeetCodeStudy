package com.bottomlord.week_039;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/4/3 8:17
 */
public class Interview_0814_1_布尔运算 {
    public int countEval(String s, int result) {
        int n = s.length();
        int[][][] memo = new int[n][n][2];
        for (int start = 0; start < n; start++) {
            for (int end = 0; end < n; end++) {
                Arrays.fill(memo[start][end], -1);
            }
        }

        return rec(0, n - 1, memo, result, s);
    }

    private int rec(int start, int end, int[][][] memo, int result, String s) {
        if (start == end) {
            return s.charAt(start) - '0' == result ? 1 : 0;
        }

        if (memo[start][end][result] != -1) {
            return memo[start][end][result];
        }

        int ans = 0;

        for (int k = start; k < end; k += 2) {
            char operator = s.charAt(k + 1);
            for (int left = 0; left <= 1; left++) {
                for (int right = 0; right <= 1; right++) {
                    if (cal(left, right, operator) == result) {
                        ans += rec(start, k, memo, left, s) * rec(k + 2, end, memo, right, s);
                    }
                }
            }
        }

        memo[start][end][result] = ans;
        return ans;
    }

    private int cal(int left, int right, char operator) {
        if (operator == '|') {
            return left | right;
        } else if (operator == '&') {
            return left & right;
        } else {
            return left ^ right;
        }
    }
}
