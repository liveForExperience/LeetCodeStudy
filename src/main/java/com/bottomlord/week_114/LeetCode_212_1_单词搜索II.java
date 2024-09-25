package com.bottomlord.week_114;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-09-16 08:34:33
 */
public class LeetCode_212_1_单词搜索II {
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        int row = board.length, col = board[0].length;
        Map<Character, List<int[]>> mapping = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                mapping.computeIfAbsent(board[i][j], x -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (word == null || word.length() == 0) {
                continue;
            }

            List<int[]> indexes = mapping.get(word.charAt(0));
            if (indexes == null) {
                continue;
            }

            for (int[] index : indexes) {
                if (backTrack(index[0], index[1], row, col, 0, word, board, new boolean[row][col])) {
                    ans.add(word);
                    break;
                }
            }
        }

        return ans;
    }

    private boolean backTrack(int x, int y, int row, int col, int index, String word, char[][] board, boolean[][] memo) {
        if (index == word.length()) {
            return true;
        }

        if (x < 0 || x >= row || y < 0 || y >= col || memo[x][y] || board[x][y] != word.charAt(index)) {
            return false;
        }

        memo[x][y] = true;
        for (int[] direction : directions) {
            if (backTrack(x + direction[0], y + direction[1], row, col, index + 1, word, board, memo)) {
                return true;
            }
        }
        return memo[x][y] = false;
    }
}
