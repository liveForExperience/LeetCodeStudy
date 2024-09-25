package com.bottomlord.week_136;

/**
 * @author chen yue
 * @date 2022-02-16 13:23:18
 */
public class LeetCode_offerII72_1_求平方根 {
    public int mySqrt(int x) {
        long head = 0, tail = x;
        while (head < tail) {
            long mid = head + (tail - head + 1) / 2;
            long val = mid * mid;
            if (val == x) {
                return (int)mid;
            } else if (val < x) {
                head = mid;
            } else {
                tail = mid - 1;
            }
        }
        return (int)head;
    }
}
