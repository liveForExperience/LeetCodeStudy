package com.bottomlord.week_010;

public class LeetCode_861_1_翻转矩阵后的得分 {
    public int matrixScore(int[][] A) {
        int ans = 0, row = A.length, col = A[0].length;
        for (int c = 0; c < col; c++) {
            int one = 0;
            for (int r = 0; r < row; r++) {
                one += A[r][c] ^ A[r][0];
            }

            ans += Math.max(row - one, one) * (1 << col - 1 - c);
        }
        return ans;
    }
}
