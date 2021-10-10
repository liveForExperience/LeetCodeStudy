package com.bottomlord.week_117;

/**
 * @author chen yue
 * @date 2021-10-10 10:11:25
 */
public class LeetCode_441_2 {
    public int arrangeCoins(int n) {
        long l = 1, r = n;
        while (l <= r) {
            long mid = l + (r - l) / 2,
                cost = (1 + mid) * mid >> 1;

            if (cost == n) {
                return (int) mid;
            } else if (cost < n) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return (int) r;
    }
}
