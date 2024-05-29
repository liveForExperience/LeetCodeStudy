package com.bottomlord.week_254;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2024-05-26 23:02:06
 */
public class LeetCode_1738_1_找出第K大的异或坐标值 {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        int[] result = new int[m * n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pre[i + 1][j + 1] = pre[i][j + 1] ^ pre[i + 1][j] ^ pre[i][j] ^ matrix[i][j];
                result[index++] = pre[i + 1][j + 1];
            }
        }

        Arrays.sort(result);
        return result[m * n - k];
    }
}
