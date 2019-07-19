package com.bottomlord.week_2;

/**
 * @author LiveForExperience
 * @date 2019/7/19 13:20
 */
public class LeetCode_999_1_车的可用捕获量 {
    public int numRookCaptures(char[][] board) {
        int ans = 0;
        int row = -1, col = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'R') {
                    row = i;
                    col = j;
                    break;
                }
            }

            if (row != -1) {
                break;
            }
        }

        for (int i = -1; i < 2; i += 2) {
            ans += recurse(board, row, col, i, 0);
        }

        for (int i = -1; i < 2; i += 2) {
            ans += recurse(board, row, col, 0, i);
        }

        return ans;
    }

    private int recurse(char[][] board, int row, int col, int rowStep, int colStep) {
        if (row > board.length - 1 || row < 0 || col > board[row].length - 1 || col < 0) {
            return 0;
        }

        if (board[row][col] == 'p') {
            return 1;
        }

        if (board[row][col] == 'B') {
            return 0;
        }

        return recurse(board, row + rowStep, col + colStep, rowStep, colStep);
    }

    public static void main(String[] args) {
        LeetCode_999_1_车的可用捕获量 test = new LeetCode_999_1_车的可用捕获量();
        test.numRookCaptures(new char[][]{{'.','.','.','.','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'.','.','.','R','.','.','.','p'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'}});
    }
}
