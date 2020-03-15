package com.bottomlord.contest_20200315;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/3/15 12:28
 */
public class Contest_1_1_矩阵中的幸运数 {
    public List<Integer> luckyNumbers (int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return Collections.emptyList();
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            int rowMin = Integer.MAX_VALUE, ri = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < rowMin) {
                    rowMin = matrix[i][j];
                    ri = j;
                }
            }

            int colMax = matrix[i][ri];
            boolean isMax = true;
            for (int k = 0; k < matrix.length; k++) {
                if (colMax < matrix[k][ri]) {
                    isMax = false;
                    break;
                }
            }

            if (isMax) {
                ans.add(colMax);
            }
        }

        return ans;
    }
}
