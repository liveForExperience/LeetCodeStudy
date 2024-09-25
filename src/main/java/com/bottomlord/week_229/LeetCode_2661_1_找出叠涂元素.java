package com.bottomlord.week_229;

/**
 * @author chen yue
 * @date 2023-12-01 21:41:50
 */
public class LeetCode_2661_1_找出叠涂元素 {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] r = new int[m], c = new int[n], mapping = new int[m * n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = mat[i][j];
                mapping[num] = i * n + j;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            int index = mapping[arr[i]];
            int row = index / n, col = index % n;
            if (++r[row] == n || ++c[col] == m) {
                return i;
            }
        }

        return -1;
    }
}
