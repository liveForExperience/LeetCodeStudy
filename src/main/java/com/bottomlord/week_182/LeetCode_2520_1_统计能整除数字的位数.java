package com.bottomlord.week_182;

/**
 * @author chen yue
 * @date 2023-01-08 13:21:17
 */
public class LeetCode_2520_1_统计能整除数字的位数 {
    public int countDigits(int num) {
        int n = num, ans = 0;
        while (n > 0) {
            int digit = n % 10;
            if (digit == 0) {
                continue;
            }

            ans += num % digit == 0 ? 1 : 0;
            n /= 10;
        }

        return ans;
    }
}
