package com.bottomlord.week_025;

/**
 * @author ThinkPad
 * @date 2019/12/24 8:59
 */
public class LeetCode_357_1_计算各个位数不同的数字个数 {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }

        int[] dp = new int[11];
        dp[0] = 1;

        n = Math.min(n, 10);
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + fun(i);
        }

        return dp[n];
    }

    private int fun(int n) {
        if (n == 1) {
            return 9;
        }

        int ans = 9, num = 9;
        for (int i = 1; i < n; i++) {
            ans *= num--;
        }

        return ans;
    }
}
