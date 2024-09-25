package com.bottomlord.week_050;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/6/18 17:42
 */
public class LeetCode_51_1_N皇后 {
    int[] row, col, pie, na, queen;
    List<List<String>> ans;
    int num;
    public List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        if (n <= 0) {
            return ans;
        }

        num = n;
        row = new int[n];
        col = new int[n];
        pie = new int[2 * n - 1];
        na = new int[4 * n - 1];
        queen = new int[n];

        backtrack(0);
        return ans;
    }

    private void backtrack(int r) {
        for (int c = 0; c < num; c++) {
            if (isValid(r, c)) {
                put(r, c);
                if (r + 1 == num) {
                    generate();
                } else {
                    backtrack(r + 1);
                }
                remove(r, c);
            }
        }
    }

    private boolean isValid(int r, int c) {
        return row[r] + col[c] + pie[r + c] + na[r - c + 2 * num] == 0;
    }

    private void put(int r, int c) {
        queen[r] = c;
        row[r] = 1;
        col[c] = 1;
        pie[r + c] = 1;
        na[r - c + 2 * num] = 1;
    }

    private void remove(int r, int c) {
        queen[r] = 0;
        row[r] = 0;
        col[c] = 0;
        pie[r + c] = 0;
        na[r - c + 2 * num] = 0;
    }

    private void generate() {
        List<String> sol = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < queen[i]; j++) {
                sb.append(".");
            }
            sb.append("Q");
            for (int j = queen[i] + 1; j < num; j++) {
                sb.append(".");
            }
            sol.add(sb.toString());
        }
        ans.add(sol);
    }
}
