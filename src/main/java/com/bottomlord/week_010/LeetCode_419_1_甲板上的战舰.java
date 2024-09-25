package com.bottomlord.week_010;

public class LeetCode_419_1_甲板上的战舰 {
    private int count = 0;
    private int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int countBattleships(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 'X') {
                    count++;
                    for (int[] direction : directions) {
                        backTrack(board, row, col, direction[0], direction[1]);
                    }
                    board[row][col] = '.';
                }
            }
        }

        return count;
    }

    private void backTrack(char[][] board, int row, int col, int rowPath, int colPath) {
        int nextRow = row + rowPath, nextCol = col + colPath;
        if (nextRow < 0 || nextRow >= board.length || nextCol < 0 || nextCol >= board[row].length) {
            return;
        }

        if (board[nextRow][nextCol] == 'X') {
            backTrack(board, row + rowPath, col + colPath, rowPath, colPath);
            board[nextRow][nextCol] = '.';
        }
    }
}
