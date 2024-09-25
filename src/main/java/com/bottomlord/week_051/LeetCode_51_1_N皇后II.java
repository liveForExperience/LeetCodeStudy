package com.bottomlord.week_051;

/**
 * @author ChenYue
 * @date 2020/6/22 8:19
 */
public class LeetCode_51_1_N皇后II {
    private int ans = 0, num;
    private int[] row, col, pie, na;

    public int totalNQueens(int n) {
        num = n;
        row = new int[n];
        col = new int[n];
        pie = new int[2 * n - 1];
        na = new int[4 * n - 1];
        backtrack(0);
        return ans;
    }

    private void backtrack(int r) {
        for (int c = 0; c < num; c++) {
            if (canPut(r, c)) {
                put(r, c);
                if (r == num - 1) {
                    ans++;
                } else {
                    backtrack(r + 1);
                }
                remove(r, c);
            }
        }
    }

    private boolean canPut(int r, int c) {
        return row[r] + col[c] + pie[r + c] + na[r - c + 2 * num] == 0;
    }

    private void put(int r, int c) {
        row[r] = 1;
        col[c] = 1;
        pie[r + c] = 1;
        na[r - c + 2 * num] = 1;
    }

    private void remove(int r, int c) {
        row[r] = 0;
        col[c] = 0;
        pie[r + c] = 0;
        na[r - c + 2 * num] = 0;
    }
}
