package com.bottomlord.week_181;

import com.bottomlord.contest_20220508.Contest_3_1_统计打字方案数;

/**
 * @author chen yue
 * @date 2023-01-01 13:37:51
 */
public class LeetCode_2485_1_找出中枢整数 {
    public int pivotInteger(int n) {
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + i;
        }

        for (int i = 1; i < sums.length; i++) {
            if (sums[i] == sums[n] - sums[i - 1]) {
                return i;
            }
        }

        return -1;
    }
}
