package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/23 10:53
 */
public class Interview_29_1_顺时针打印矩阵 {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }

        int row = matrix.length, col = matrix[0].length, r = 0, c = 0, index = 0, di = 0;
        boolean[][] visited = new boolean[row][col];
        int[] ans = new int[row * col], rd = {0 , 1, 0, -1}, cd = {1, 0, -1, 0};

        for (int i = 0; i < row * col; i++) {
            ans[index++] = matrix[r][c];
            visited[r][c] = true;

            int newR = r + rd[di], newC = c + cd[di];

            if (newR >= 0 && newR < row && newC >= 0 && newC < col && !visited[newR][newC]) {
                r = newR;
                c = newC;
            } else {
                di = (di + 1) % 4;
                r += rd[di];
                c += cd[di];
            }
        }

        return ans;
    }
}
