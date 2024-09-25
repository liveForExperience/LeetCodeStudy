package com.bottomlord.week_011;

public class LeetCode_289_1_生命游戏 {
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                judge(board, i, j);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = board[i][j] == 1 || board[i][j] == -2 ? 1 : 0;
            }
        }
    }

    private void judge(int[][] board, int x, int y) {
        int left = Math.max(0, y - 1);
        int right = Math.min(board[x].length - 1, y + 1);
        int top = Math.max(0, x - 1);
        int bottom = Math.min(board.length - 1, x + 1);
        int count = 0;
        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                count = board[i][j] == 1 || board[i][j] == -1 ? count + 1 : count;
            }
        }

        board[x][y] = board[x][y] == 1 ? (count == 3 || count == 4 ? 1 : -1) : (count == 3 ? -2 : 0);
    }
}
