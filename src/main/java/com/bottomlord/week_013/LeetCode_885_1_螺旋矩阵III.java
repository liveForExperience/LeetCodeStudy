package com.bottomlord.week_013;

public class LeetCode_885_1_螺旋矩阵III {
    private int[] dx = {0, 1, 0, -1};
    private int[] dy = {1, 0, -1, 0};
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int index = 0;

        int[][] ans = new int[R * C][2];
        ans[index++] = new int[]{r0, c0};
        if (R * C == 1) {
            return ans;
        }

        for (int i = 1; i < 2 * R * C; i += 2) {
            for (int j = 0; j < 4; j++) {
                int time = i + j / 2;
                for (int k = 0; k < time; k++) {
                    r0 += dx[j];
                    c0 += dy[j];

                    if (isValid(R, C, r0, c0)) {
                        ans[index++] = new int[]{r0, c0};
                        if (index == R * C) {
                            return ans;
                        }
                    }
                }
            }
        }
        return ans;
    }

    private boolean isValid(int R, int C, int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}
