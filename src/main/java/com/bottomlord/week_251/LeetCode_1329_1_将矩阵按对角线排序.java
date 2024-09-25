package com.bottomlord.week_251;

/**
 * @author chen yue
 * @date 2024-04-29 08:07:37
 */
public class LeetCode_1329_1_将矩阵按对角线排序 {
        public int[][] diagonalSort(int[][] mat) {
            int row = mat.length, col = mat[0].length, index = 0;
            int[][] bucket = new int[row + col][101];

            for (int i = 0; i < row; i++) {
                int r = i, c = 0;
                for (; r < row && c < col; r++, c++) {
                    bucket[index][mat[r][c]]++;
                }

                r = i; c = 0;
                for (int j = 0; j < bucket[index].length;) {
                    if (bucket[index][j] == 0) {
                        j++;
                        continue;
                    }

                    bucket[index][j]--;
                    mat[r++][c++] = j;

                    if (bucket[index][j] == 0) {
                        j++;
                    }
                }

                index++;
            }

            for (int i = 0; i < col; i++) {
                int r = 0, c = i;
                for (; r < row && c < col; r++, c++) {
                    bucket[index][mat[r][c]]++;
                }

                r = 0; c = i;
                for (int j = 0; j < bucket[index].length;) {
                    if (bucket[index][j] == 0) {
                        j++;
                        continue;
                    }

                    bucket[index][j]--;
                    mat[r++][c++] = j;

                    if (bucket[index][j] == 0) {
                        j++;
                    }
                }

                index++;
            }

            return mat;
        }
}
