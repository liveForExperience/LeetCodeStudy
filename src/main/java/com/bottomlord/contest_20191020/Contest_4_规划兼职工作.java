package com.bottomlord.contest_20191020;

import java.util.Arrays;
import java.util.Comparator;

public class Contest_4_规划兼职工作 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = profit.length;
        int[] dp = new int[len];
        Integer[] jobs = new Integer[len];
        for (int i = 0; i < len; i++) {
            jobs[i] = i;
        }

        Arrays.fill(dp, -1);
        Arrays.sort(jobs, Comparator.comparing(x -> startTime[(int) x]).thenComparing(x -> endTime[(int) x]));

        return recurse(dp, jobs, profit, startTime, endTime, 0, len);
    }

    private int recurse(int[] dp, Integer[] jobs, int[] profit, int[] startTime, int[] endTime, int startJob, int jobNums) {
        if (startJob == jobNums) {
            return 0;
        }

        if (dp[startJob] != -1) {
            return dp[startJob];
        }

        int next = recurse(dp, jobs, profit, startTime, endTime, startJob + 1, jobNums);
        int cur = profit[jobs[startJob]];
        for (int i = startJob + 1; i < jobNums; i++) {
            if (startTime[jobs[i]] >= endTime[jobs[i]]) {
                cur += recurse(dp, jobs, profit, startTime, endTime, i, jobNums);
                break;
            }
        }

        int ans = Math.max(next, cur);

        return dp[startJob] = ans;
    }
}
