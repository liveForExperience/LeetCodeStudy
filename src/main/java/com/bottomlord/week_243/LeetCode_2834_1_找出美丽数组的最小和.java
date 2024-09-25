package com.bottomlord.week_243;

/**
 * @author chen yue
 * @date 2024-03-09 14:44:08
 */
public class LeetCode_2834_1_找出美丽数组的最小和 {
    public int minimumPossibleSum(int n, int target) {
        int m = target / 2, mod = (int) 1e9 + 7;
        if (n <= m) {
            return (int) (((long) (1 + n) * n / 2) % mod);
        }

        return (int) (((long) (1 + m) * m / 2 + ((long) target + target + (n - m) - 1) * (n - m) / 2) % mod);
    }
}
