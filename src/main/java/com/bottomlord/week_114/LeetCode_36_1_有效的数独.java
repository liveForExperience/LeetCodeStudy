package com.bottomlord.week_114;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-09-17 08:57:39
 */
public class LeetCode_36_1_有效的数独 {
    public boolean isValidSudoku(char[][] board) {
        Map<String, Set<Character>> mapping = new HashMap<>();
        boolean[][] rows = new boolean[9][9], cols = new boolean[9][9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }

                if (rows[i][c - 'a']) {
                    return false;
                }
                rows[i][c - 'a'] = true;

                if (cols[j][c - 'a']) {
                    return false;
                }
                cols[j][c - 'a'] = true;

                String key = i / 3 + ":" + (j / 3);
                boolean add = mapping.computeIfAbsent(key, x -> new HashSet<>()).add(c);
                if (!add) {
                    return false;
                }
            }
        }

        return true;
    }
}
