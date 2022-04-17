package com.bottomlord.contest_20220417;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-04-17 11:40:43
 */
public class Contest_4_1_相邻字符不同的最长路径 {
    private List<Integer>[] g;
    private int n, ans;
    private String s;
    public int longestPath(int[] parent, String s) {
        this.n = parent.length;
        this.s = s;
        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            g[parent[i]].add(i);
        }

        dfs(0);
        return ans + 1;
    }

    private int dfs(int parent) {
        if (parent >= n) {
            return 0;
        }

        List<Integer> list = g[parent];
        int maxLen = 0;
        for (Integer child : list) {
            int len = dfs(child) + 1;

            if (s.charAt(parent) != s.charAt(child)) {
                ans = Math.max(ans, maxLen + len);
                maxLen = Math.max(len, maxLen);
            }
        }

        return maxLen;
    }
}
