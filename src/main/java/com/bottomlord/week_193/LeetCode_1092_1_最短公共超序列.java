package com.bottomlord.week_193;

/**
 * @author chen yue
 * @date 2023-03-28 08:46:10
 */
public class LeetCode_1092_1_最短公共超序列 {

    public String shortestCommonSupersequence(String str1, String str2) {
        return dfs(str1, str2, new String[str1.length()][str2.length()]);
    }

    private String dfs(String str1, String str2, String[][] memo) {
        if (str1.isEmpty()) {
            return str2;
        }

        if (str2.isEmpty()) {
            return str1;
        }

        int n1 = str1.length(), n2 = str2.length();
        if (memo[n1 - 1][n2 - 1] != null) {
            return memo[n1 - 1][n2 - 1];
        }

        char c1 = str1.charAt(n1 - 1), c2 = str2.charAt(n2 - 1);
        String ns1 = str1.substring(0, n1 - 1), ns2 = str2.substring(0, n2 - 1);

        if (c1 == c2) {
            return memo[n1 - 1][n2 - 1] = dfs(ns1, ns2, memo) + c1;
        } else {
            String x = dfs(ns1, str2, memo),
                   y = dfs(str1, ns2, memo);

            return memo[n1 - 1][n2 - 1] = (x.length() < y.length() ? x + c1 : y + c2);
        }
    }
}
