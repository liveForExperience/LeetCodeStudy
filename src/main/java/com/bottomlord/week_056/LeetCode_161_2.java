package com.bottomlord.week_056;

/**
 * @author ChenYue
 * @date 2020/7/28 18:35
 */
public class LeetCode_161_2 {
    public boolean isOneEditDistance(String s, String t) {
        int sl = s.length(), tl = t.length();
        if (Math.abs(sl - tl) > 1) {
            return false;
        }

        int[][] dp = new int[sl + 1][tl + 1];
        for (int i = 0; i <= sl; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= tl; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < sl; i++) {
            for (int j = 1; j < tl; j++) {
                int sAdd = dp[i - 1][j] + 1, tAdd = dp[i][j - 1] + 1,
                    edit = s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(sAdd, Math.min(tAdd, edit));
            }
        }

        return dp[sl][tl] == 1;
    }
}