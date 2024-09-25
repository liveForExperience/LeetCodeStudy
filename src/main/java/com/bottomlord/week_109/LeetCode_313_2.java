package com.bottomlord.week_109;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/8/9 8:57
 */
public class LeetCode_313_2 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        long[] dp = new long[n + 1];
        dp[1] = 1;

        int[] points = new int[primes.length];
        Arrays.fill(points, 1);

        for (int i = 2; i <= n; i++) {
            long min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(dp[points[j]] * primes[j], min);
            }
            dp[i] = min;

            for (int j = 0; j < primes.length; j++) {
                if (dp[i] == primes[j] * dp[points[j]]) {
                    points[j]++;
                }
            }
        }

        return (int)dp[n];
    }
}
