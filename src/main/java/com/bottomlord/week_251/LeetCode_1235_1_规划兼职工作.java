package com.bottomlord.week_251;

import com.bottomlord.LeetCodeUtils;

import java.util.Arrays;
import java.util.Comparator;

import static com.bottomlord.LeetCodeUtils.convertToIntArr;

/**
 * @author chen yue
 * @date 2024-05-04 22:58:53
 */
public class LeetCode_1235_1_规划兼职工作 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];

        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        Arrays.sort(jobs, Comparator.comparingInt(x -> x[1]));

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int k = binarySearch(jobs, i - 1);
            dp[i] = Math.max(dp[i - 1], dp[k + 1] + jobs[i - 1][2]);
        }

        return dp[n];
    }

    private int binarySearch(int[][] jobs, int i) {
        int left = -1, right = i, startTime = jobs[i][0];
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (jobs[mid][1] <= startTime) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
