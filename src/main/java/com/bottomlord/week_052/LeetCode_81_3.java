package com.bottomlord.week_052;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/7/1 14:38
 */
public class LeetCode_81_3 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int col = matrix[0].length, ans = 0;
        int[] hDp = new int[col],
              lDp = new int[col],
              rDp = new int[col];

        Arrays.fill(rDp, col - 1);

        for (char[] chars : matrix) {
            for (int c = 0; c < col; c++) {
                hDp[c] = chars[c] == '1' ? hDp[c] + 1 : 0;
            }

            int curL = 0;
            for (int c = 0; c < col; c++) {
                if (chars[c] == '1') {
                    lDp[c] = Math.max(curL, lDp[c]);
                } else {
                    lDp[c] = 0;
                    curL = c + 1;
                }
            }

            int curR = col - 1;
            for (int c = col - 1; c >= 0; c--) {
                if (chars[c] == '1') {
                    rDp[c] = Math.min(curR, rDp[c]);
                } else {
                    rDp[c] = col - 1;
                    curR = c - 1;
                }
            }

            for (int c = 0; c < col; c++) {
                ans = Math.max(ans, hDp[c] * (rDp[c] - lDp[c]));
            }
        }

        return ans;
    }
}
