package com.bottomlord.week_051;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/6/25 22:48
 */
public class LeetCode_139_3 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }
}
