package com.bottomlord.week_193;

/**
 * @author chen yue
 * @date 2023-03-29 22:17:12
 */
public class LeetCode_1092_2 {
    private String s1, s2;
    private int[][] memo;

    public String shortestCommonSupersequence(String str1, String str2) {
        s1 = str1;
        s2 = str2;
        memo = new int[s1.length()][s2.length()];
        return dfs2(s1.length() - 1, s2.length() - 1);
    }

    private int dfs(int i, int j) {
        if (i < 0) {
            return j + 1;
        }

        if (j < 0) {
            return i + 1;
        }

        if (memo[i][j] > 0) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return memo[i][j] = dfs(i - 1, j - 1) + 1;
        } else {
            int len1 = dfs(i - 1, j), len2 = dfs(i, j - 1);
            return memo[i][j] = len1 <= len2 ? len1 + 1 : len2 + 1;
        }
    }

    private String dfs2(int i, int j) {
        if (i < 0) {
            return s2.substring(0, j + 1);
        }

        if (j < 0) {
            return s1.substring(0, i + 1);
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return dfs2(i - 1, j - 1) + s1.charAt(i);
        }

        if (dfs(i - 1, j) <= dfs(i, j - 1)) {
            return dfs2(i - 1, j) + s1.charAt(i);
        } else {
            return dfs2(i, j - 1) + s2.charAt(j);
        }
    }
}
