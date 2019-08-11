package com.bottomlord.week_5;

public class LeetCode_661_1_图片平滑器 {
    public int[][] imageSmoother(int[][] M) {
        if (M.length == 0) {
            return new int[0][0];
        }

        int[][] ans = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                int count = 1, sum = M[i][j];
                if (i - 1 >= 0 && j - 1 >= 0) {
                    count++;
                    sum += M[i - 1][j - 1];
                }

                if (i + 1 < M.length && j + 1 < M[i].length) {
                    count++;
                    sum += M[i + 1][j + 1];
                }

                if (i - 1 >= 0 && j + 1 < M[i].length) {
                    count++;
                    sum += M[i - 1][j + 1];
                }

                if (i + 1 < M.length && j - 1 >= 0) {
                    count++;
                    sum += M[i + 1][j - 1];
                }

                if (i - 1 >= 0) {
                    count++;
                    sum += M[i - 1][j];
                }

                if (j - 1 >= 0) {
                    count++;
                    sum += M[i][j - 1];
                }

                if (i + 1 < M.length) {
                    count++;
                    sum += M[i + 1][j];
                }

                if (j + 1 < M[i].length) {
                    count++;
                    sum += M[i][j + 1];
                }

                ans[i][j] = sum / count;
            }
        }

        return ans;
    }
}
