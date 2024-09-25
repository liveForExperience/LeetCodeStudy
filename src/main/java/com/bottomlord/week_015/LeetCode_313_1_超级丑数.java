package com.bottomlord.week_015;

public class LeetCode_313_1_超级丑数 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        dp[0] = 1;
        int[] pos = new int[primes.length];

        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, dp[pos[j]] * primes[j]);
            }

            for (int j = 0; j < primes.length; j++) {
                if (min == dp[pos[j]] * primes[j]) {
                    pos[j]++;
                    dp[i] = min;
                    break;
                }
            }
        }
        return dp[n - 1];
    }
}
