package com.bottomlord.contest_20190929;

public class Contest_2_尽可能使字符串相等 {
    public int equalSubstring(String s, String t, int maxCost) {
        int len = s.length(), max = 0;
        int[] dp = new int[len];
        dp[0] = Math.abs(s.charAt(0) - t.charAt(0));
        if (dp[0] <= maxCost) {
            max = 1;
        }

        for (int i = 1; i < s.length(); i++) {
            dp[i] = dp[i - 1] + Math.abs(s.charAt(i) - t.charAt(i));
            if (dp[i] <= maxCost) {
                max = Math.max(max, i + 1);
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = i; j < dp.length; j++) {
                int cost = dp[j] - dp[i - 1];
                if (cost <= maxCost) {
                    max = Math.max(max, j - i + 1);
                } else {
                    break;
                }
            }
        }
        return max;
    }
}
