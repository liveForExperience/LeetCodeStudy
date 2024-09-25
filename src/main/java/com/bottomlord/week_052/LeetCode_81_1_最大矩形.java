package com.bottomlord.week_052;

/**
 * @author ChenYue
 * @date 2020/6/30 10:40
 */
public class LeetCode_81_1_最大矩形 {
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }

        int col = matrix[0].length, ans = 0;
        int[][] dp = new int[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == '1') {
                    dp[r][c] = c == 0 ? 1 : dp[r][c - 1] + 1;

                    int width = dp[r][c];
                    for (int i = r; i >= 0; i--) {
                        width = Math.min(width, dp[i][c]);
                        ans = Math.max(width * (r - i + 1), ans);
                    }
                }
            }
        }

        return ans;
    }
}
