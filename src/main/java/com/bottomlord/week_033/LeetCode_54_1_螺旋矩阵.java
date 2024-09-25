package com.bottomlord.week_033;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/2/23 10:55
 */
public class LeetCode_54_1_螺旋矩阵 {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return Collections.emptyList();
        }

        int row = matrix.length, col = matrix[0].length, r = 0, c = 0, index = 0, di = 0;
        boolean[][] visited = new boolean[row][col];
        int[] rd = {0 , 1, 0, -1}, cd = {1, 0, -1, 0};
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < row * col; i++) {
            ans.add(matrix[r][c]);
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
