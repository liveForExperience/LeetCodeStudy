package com.bottomlord.week_016;

public class LeetCode_62_1_不同路径 {
    private int sum = 0;

    public int uniquePaths(int m, int n) {
        recurse(0, 0, m, n);
        return sum;
    }

    private void recurse(int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return;
        }

        if (r == m - 1 && c == n - 1) {
            sum++;
            return;
        }

        recurse(r + 1, c, m, n);
        recurse(r, c + 1, m, n);
    }
}
