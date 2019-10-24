package com.bottomlord.week_016;

import java.util.HashSet;

public class LeetCode_36_2 {
    public boolean isValidSudoku(char[][] board) {
        HashSet[] rowArr = new HashSet[9];
        HashSet[] colArr = new HashSet[9];
        HashSet[] boxArr = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rowArr[i] = new HashSet<Integer>();
            colArr[i] = new HashSet<Integer>();
            boxArr[i] = new HashSet<Integer>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int box = i / 3 * 3 + j / 3;

                    if (rowArr[i].contains(c) || colArr[j].contains(c) || boxArr[box].contains(c)) {
                        break;
                    }

                    rowArr[i].add(c);
                    colArr[j].add(c);
                    boxArr[box].add(c);
                }
            }
        }

        return true;
    }
}