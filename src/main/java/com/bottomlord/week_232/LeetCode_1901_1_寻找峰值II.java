package com.bottomlord.week_232;

/**
 * @author chen yue
 * @date 2023-12-19 15:28:05
 */
public class LeetCode_1901_1_寻找峰值II {
    public int[] findPeakGrid(int[][] mat) {
        int head = 0, tail = mat.length - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2, max = 0, k = -1;
            for (int i = 0; i < mat[mid].length; i++) {
                if (mat[mid][i] > max) {
                    max = mat[mid][i];
                    k = i;
                }
            }

            if (mat[mid][k] < mat[mid + 1][k]) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        int max = 0, k = -1;
        for (int i = 0; i < mat[head].length; i++) {
            if (mat[head][i] > max) {
                max = mat[head][i];
                k = i;
            }
        }

        return new int[]{head, k};
    }
}
