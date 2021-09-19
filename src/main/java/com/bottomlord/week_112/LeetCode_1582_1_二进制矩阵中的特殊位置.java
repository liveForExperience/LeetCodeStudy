package com.bottomlord.week_112;

/**
 * @author chen yue
 * @date 2021-09-02 08:33:18
 */
public class LeetCode_1582_1_二进制矩阵中的特殊位置 {
    public int numSpecial(int[][] mat) {
        int r = mat.length, c = mat[0].length;
        int[] row = new int[r], col = new int[c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (mat[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (row[i] == 1 && col[j] == 1 && mat[i][j] == 1) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
