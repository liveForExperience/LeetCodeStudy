package com.bottomlord.week_011;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_131_2 {
    public List<List<String>> partition(String s) {
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        for (int len = 1; len <= length; len++) {
            for (int i = 0; i <= length - len; i++) {
                int j = i + len - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) && (len < 3 || dp[i + 1][j - 1]);
            }
        }
        return recurse(s, 0, dp);
    }

    private List<List<String>> recurse(String s, int start, boolean[][] dp) {
        List<List<String>> ans = new ArrayList<>();
        if (s.length() == start) {
            List<String> list = new ArrayList<>();
            ans.add(list);
            return ans;
        }

        for (int i = start; i < s.length(); i++) {
            if (dp[start][i]) {
                String left = s.substring(start, i + 1);
                for (List<String> list : recurse(s, i + 1, dp)) {
                    list.add(0, left);
                    ans.add(list);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        LeetCode_131_2 t = new LeetCode_131_2();
        t.partition("aab");
    }
}