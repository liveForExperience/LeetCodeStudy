package com.bottomlord.week_005;

public class LeetCode_661_2 {
    public int[][] imageSmoother(int[][] M) {
        if (M.length == 0) {
            return new int[0][0];
        }

        int[][] ans = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                ans[i][j] = compute(M, i, j, M.length, M[i].length);
            }
        }

        return ans;
    }

    private int compute(int[][] M, int x, int y, int xLen, int yLen) {
        int count = 0, sum = 0;
        for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, xLen - 1); i++) {
            for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, yLen - 1); j++) {
                count++;
                sum += M[i][j];
            }
        }
        return sum / count;
    }
}
