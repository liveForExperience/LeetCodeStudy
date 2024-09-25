package com.bottomlord.week_236;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2024-01-05 10:19:52
 */
public class LeetCode_466_1_1_统计重复个数 {

    private final int mod = 1000000007;
    private int minSum, maxSum;
    private final int[][] dp = new int[23][230];
    private String num;
    public int count(String num1, String num2, int min_sum, int max_sum) {
        for (int[] nums : dp) {
            Arrays.fill(nums, -1);
        }
        this.minSum = min_sum;
        this.maxSum = max_sum;
        return (doCount(num2) - doCount(subOne(num1)) + mod) % mod;
    }

    private int doCount(String num) {
        this.num = num;
        return dfs(0, 0, false);
    }

    private String subOne(String num) {
        char[] cs = num.toCharArray();
        int i;
        for (i = cs.length - 1; i >= 0; i--) {
            if (cs[i] == '0') {
                continue;
            }

            cs[i]--;
            break;
        }

        for (int j = i + 1; j < cs.length; j++) {
            cs[j] = '9';
        }

        return new String(cs);
    }

    private int dfs(int i, int sum, boolean flag) {
        if (sum > maxSum) {
            return 0;
        }

        if (i <= 0) {
            return sum >= minSum ? 1 : 0;
        }

        if (!flag && dp[i][sum] != -1) {
            return dp[i][sum];
        }

        int up = flag ? (num.charAt(i) - '0') : 9, cnt = 0;
        for (int j = 0; j <= up; j++) {
            cnt = (cnt + dfs(i + 1, sum + j, flag && j == up)) % mod;
        }

        if (!flag) {
            dp[i][sum] = cnt;
        }

        return cnt;
    }
    //todo 确定字符串为什么要反转，从高位遍历到低位，且低位是0
}
