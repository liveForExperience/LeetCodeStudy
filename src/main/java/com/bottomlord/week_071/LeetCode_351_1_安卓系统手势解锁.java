package com.bottomlord.week_071;

/**
 * @author ChenYue
 * @date 2020/11/19 8:53
 */
public class LeetCode_351_1_安卓系统手势解锁 {
    public int numberOfPatterns(int m, int n) {
        int sum = 0;
        for (int i = m; i <= n; i++) {
            sum += backTrack(-1, i, new boolean[9]);
        }
        return sum;
    }

    private int backTrack(int last, int len, boolean[] memo) {
        if (len == 0) {
            return 1;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            if (isValid(last, i, memo)) {
                memo[i] = true;
                sum += backTrack(i, len - 1, memo);
                memo[i] = false;
            }
        }
        return sum;
    }

    private boolean isValid(int last, int index, boolean[] memo) {
        if (memo[index]) {
            return false;
        }

        if (last == -1) {
            return true;
        }

        if ((index + last) % 2 == 1) {
            return true;
        }

        int mid = (last + index) / 2;
        if (mid == 4) {
            return memo[mid];
        }

        if ((index % 3 != last % 3) && (index / 3) != (last / 3)) {
            return true;
        }

        return memo[mid];
    }
}
