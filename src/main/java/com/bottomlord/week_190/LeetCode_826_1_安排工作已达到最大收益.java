package com.bottomlord.week_190;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-02-27 20:35:27
 */
public class LeetCode_826_1_安排工作已达到最大收益 {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        Integer[] is = new Integer[n];
        for (int i = 0; i < n; i++) {
            is[i] = i;
        }

        Arrays.sort(is, (x, y) -> profit[y] - profit[x]);

        int sum = 0;
        for (int w : worker) {
            for (Integer i : is) {
                if (w >= difficulty[i]) {
                    sum += profit[i];
                    break;
                }
            }
        }

        return sum;
    }
}
