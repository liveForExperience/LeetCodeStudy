package com.bottomlord.week_253;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chen yue
 * @date 2024-05-15 22:10:54
 */
public class LeetCode_2589_1_完成所有任务的最少时间 {
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, Comparator.comparingInt(x -> x[1]));
        int max = tasks[tasks.length - 1][1];
        boolean[] memo = new boolean[max + 1];
        int ans = 0;
        for (int[] task : tasks) {
            int start = task[0], end = task[1], duration = task[2];
            for (int i = start; i <= end; i++) {
                if (memo[i]) {
                    duration--;
                }
            }

            for (int i = end; i > 0 && duration > 0 ; i--) {
                if (!memo[i]) {
                    memo[i] = true;
                    duration--;
                    ans++;
                }
            }
        }

        return ans;
    }
}
