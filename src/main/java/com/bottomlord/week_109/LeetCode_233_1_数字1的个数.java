package com.bottomlord.week_109;

/**
 * @author ChenYue
 * @date 2021/8/13 8:22
 */
public class LeetCode_233_1_数字1的个数 {
    public int countDigitOne(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i *= 10) {
            int high = n / (i * 10),
                low = n % i,
                cur = (n - high * i * 10 - low) / i;

            if (cur == 0) {
                ans += high * i;
            } else if (cur == 1) {
                ans += high * i + 1 + low;
            } else {
                ans += (high + 1) * i;
            }
        }

        return ans;
    }
}
