package com.bottomlord.week_015;

public class LeetCode_529_1_扫雷游戏 {
    private int[][] directions = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        } else {
            recurse(board, click[0], click[1]);
        }

        return board;
    }

    private void recurse(char[][] board, int row, int col) {
        if (isInValid(board, row, col)) {
            return;
        }

        if (board[row][col] != 'E') {
            return;
        }

        int count = countM(board, row, col);
        if ( count == 0) {
            board[row][col] = 'B';

            for (int[] direction : directions) {
                recurse(board, row + direction[0], col + direction[1]);
            }
        } else {
            board[row][col] = Character.forDigit(count, 10);
        }
    }

    private int countM(char[][] board, int row, int col) {
        int count = 0;
        for (int[] direction : directions) {
            if (isInValid(board, row + direction[0], col + direction[1])) {
                continue;
            }
            count += board[row + direction[0]][col + direction[1]] == 'M' ? 1 : 0;
        }
        return count;
    }

    private boolean isInValid(char[][]board, int row, int col) {
        return row < 0 || row >= board.length || col < 0 || col >= board[row].length;
    }
}
