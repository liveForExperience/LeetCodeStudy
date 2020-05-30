package com.bottomlord.week_047;

/**
 * @author ChenYue
 * @date 2020/5/28 8:21
 */
public class Interview_1724_1_最大子矩阵 {
    public int[] getMaxMatrix(int[][] matrix) {
        int rowLen = matrix.length, colLen = matrix[0].length,
            max = Integer.MIN_VALUE;

        int[] ans = new int[4];

        for (int startRow = 0; startRow < rowLen; startRow++) {
            int[] colSum = new int[colLen];
            for (int row = startRow; row < rowLen; row++) {
                int sum = 0;
                for (int col = 0; col < colLen; col++) {
                    colSum[col] += matrix[row][col];
                    if (sum > 0) {
                        sum += colSum[col];
                    } else {
                        sum = colSum[col];
                        ans[0] = startRow;
                        ans[1] = col;
                    }

                    if (sum > max) {
                        max = sum;
                        ans[2] = row;
                        ans[3] = col;
                    }
                }
            }
        }

        return ans;
    }
}
