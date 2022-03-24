package com.bottomlord.week_141;

/**
 * @author chen yue
 * @date 2022-03-24 10:27:11
 */
public class LeetCode_634_2 {
    public int findDerangement(int n) {
        if (n == 1) {
            return 0;
        }

        int mod = 1000000007;

        long two = 0, one = 1, cur = one;
        for (int i = 3; i <= n; i++) {
            cur = ((i - 1) * (one + two)) % mod;
            two = one;
            one = cur;
        }

        return (int) cur;
    }
}
