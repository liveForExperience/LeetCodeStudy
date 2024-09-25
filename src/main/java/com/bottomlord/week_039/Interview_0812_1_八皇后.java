package com.bottomlord.week_039;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/4/2 8:57
 */
public class Interview_0812_1_八皇后 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        backTrack(0, new int[n], ans, n);
        return ans;
    }

    private void backTrack(int row, int[] records, List<List<String>> ans, int n) {
        if (row == n) {
            ans.add(print(records));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(records, row, col)) {
                records[row] = col;
                backTrack(row + 1, records, ans, n);
            }
        }
    }

    private boolean isValid(int[] records, int row, int col) {
        for (int r = 0; r < row; r++) {
            if (records[r] == col || Math.abs(row - r) == Math.abs(col - records[r])) {
                return false;
            }
        }

        return true;
    }

    private List<String> print(int[] records) {
        int len = records.length;
        List<String> ans = new ArrayList<>(len);

        for (int record : records) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < len; col++) {
                if (record == col) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            ans.add(sb.toString());
        }

        return ans;
    }
}
