package com.bottomlord.week_235;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chen yue
 * @date 2023-12-09 19:52:03
 */
public class LeetCode_2008_1_出租车的最大盈利 {
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, Comparator.comparingInt(x -> x[1]));
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        int index = 0;
        for (int i = 0; i < rides.length; i++) {
            int start = rides[i][0], end = rides[i][1], tip = rides[i][2];
            long cur = end - start + tip, preMax = 0;
            int preMaxIndex = 0;
            for (int j = start; j >= 0; j--) {
                if (dp[j] == -1) {
                    continue;
                }

                preMax = dp[j];
                preMaxIndex = i;
                cur += dp[j];
                break;
            }

            dp[end] = Math.max(dp[end], cur);

            long max = preMax;
            index = Math.max(preMaxIndex, index);
            for (int j = index; j <= end; j++) {
                max = Math.max(dp[j], max);
                dp[j] = max;
                index = j;
            }
        }

        long ans = 0;
        for (int i = n; i >= 1; i--) {
            if (dp[i] != -1) {
                return dp[i];
            }
        }

        return ans;
    }
}
