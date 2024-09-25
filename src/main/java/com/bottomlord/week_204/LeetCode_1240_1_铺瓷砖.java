package com.bottomlord.week_204;

/**
 * @author chen yue
 * @date 2023-06-10 10:47:48
 */
public class LeetCode_1240_1_铺瓷砖 {
    private int min, r, c;
    private boolean[][] memo;
    public int tilingRectangle(int n, int m) {
        this.min = m * n;
        this.r = n;
        this.c = m;
        this.memo = new boolean[r][c];

        backTrack(0, 0, 0);
        return min;
    }

    private void backTrack(int x, int y, int cur) {
        if (cur >= min) {
            return;
        }

        if (x >= r) {
            min = cur;
            return;
        }

        if (y >= c) {
            backTrack(x + 1, 0, cur);
            return;
        }

        if (memo[x][y]) {
            backTrack(x, y + 1, cur);
            return;
        }

        int len = Math.min(r - x, c - y);
        while (len > 0 && !isValid(x, y, len)) {
            len--;
        }

        for (; len > 0; len--) {
            record(x, y, len, true);
            backTrack(x, y + len, cur + 1);
            record(x, y, len, false);
        }
    }

    private boolean isValid(int x, int y, int len) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (memo[x + i][y + j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private void record(int x, int y, int len, boolean flag) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                memo[x + i][y + j] = flag;
            }
        }
    }
}
