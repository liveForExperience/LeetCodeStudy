package com.bottomlord.week_253;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chen yue
 * @date 2024-05-18 08:06:57
 */
public class LeetCode_826_1_安排工作以达到最大收益 {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;

        int[][] matrix = new int[n][2];
        for (int i = 0; i < n; i++) {
            matrix[i][0] = difficulty[i];
            matrix[i][1] = profit[i];
        }
        Arrays.sort(matrix, Comparator.comparingInt(x -> x[0]));
        Arrays.sort(worker);

        int max = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, matrix[i][1]);
            matrix[i][1] = max;
        }

        int ans = 0, idx = 0;
        for (int w : worker) {
            while (idx < n && matrix[idx][0] <= w) {
                idx++;
            }

            if (idx == 0) {
                continue;
            }

            ans += matrix[idx - 1][1];
        }

        return ans;
    }
}
