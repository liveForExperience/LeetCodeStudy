package com.bottomlord.week_201;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-05-16 23:11:11
 */
public class LeetCode_1335_2 {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }

        int[] pre = new int[n + 1];
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, jobDifficulty[i]);
            pre[i + 1] = max;
        }

        for (int i = 2; i <= d; i++) {
            int[] cur = new int[n + 1];
            Arrays.fill(cur, Integer.MAX_VALUE);
            for (int j = i; j <= n; j++) {
                max = 0;
                for (int k = j; k >= i; k--) {
                    max = Math.max(max, jobDifficulty[k - 1]);
                    cur[j] = Math.min(cur[j], max + pre[k - 1]);
                }
            }
            pre = cur;
        }

        return pre[n];
    }
}