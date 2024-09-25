package com.bottomlord.week_193;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-03-22 13:42:12
 */
public class LeetCode_1626_1_无矛盾的最佳球队 {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] peoples = new int[n][2];

        for (int i = 0; i < n; i++) {
            peoples[i][0] = ages[i];
            peoples[i][1] = scores[i];
        }

        Arrays.sort(peoples, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }

            return a[1] - b[1];
        });

        int[] dp = new int[n];
        for (int i = 0; i < peoples.length; i++) {
            dp[i] = peoples[i][1];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (peoples[i][0] >= peoples[j][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + peoples[i][1]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int num : dp) {
            max = Math.max(max, num);
        }

        return max;
    }
}
