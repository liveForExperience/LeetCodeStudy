package com.bottomlord.contest_20220508;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-05-08 11:40:54
 */
public class Contest_4_2 {
    public boolean hasValidPath(char[][] grid) {
        int row = grid.length, col = grid[0].length;
        if (grid[0][0] == ')' || grid[row - 1][col - 1] == '(') {
            return false;
        }

        Set<Integer>[][] matrix = new HashSet[row][col];
        matrix[0][0] = new HashSet<>();
        matrix[0][0].add(1);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int num = grid[i][j] == '(' ? 1 : -1;
                Set<Integer> cur = new HashSet<>();
                if (i != 0) {
                    Set<Integer> set = matrix[i - 1][j];
                    for (Integer n : set) {
                        if (n + num >= 0) {
                            cur.add(n + num);
                        }
                    }
                }

                if (j != 0) {
                    Set<Integer> set = matrix[i][j - 1];
                    for (Integer n : set) {
                        if (n + num >= 0) {
                            cur.add(n + num);
                        }
                    }
                }

                matrix[i][j] = cur;
            }
        }

        return matrix[row - 1][col - 1].contains(0);
    }
}
