package com.bottomlord.week_180;

/**
 * @author chen yue
 * @date 2022-12-24 11:19:39
 */
public class LeetCode_2427_1_公因子的数目 {
    public int commonFactors(int a, int b) {
        int gcd = gcd(a, b), cnt = 0;
        for (int i = gcd; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                cnt++;
            }
        }

        return cnt;
    }

    private int gcd(int x, int y) {
        return x % y == 0 ? y : gcd(y, x % y);
    }
}
