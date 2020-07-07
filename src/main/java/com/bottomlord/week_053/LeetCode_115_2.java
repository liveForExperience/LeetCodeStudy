package com.bottomlord.week_053;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/7/7 8:42
 */
public class LeetCode_115_2 {
    public int numDistinct(String s, String t) {
        int[][] memo = new int[s.length()][t.length()];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return backTrack(s, 0, t, 0, memo);
    }

    private int backTrack(String s, int si, String t, int ti, int[][] memo) {
        if (si == s.length() && ti != t.length()) {
            return 0;
        }

        if (ti == t.length()) {
            return 1;
        }

        if (memo[si][ti] != -1) {
            return memo[si][ti];
        }

        int count = 0;
        for (int i = si; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(ti)) {
                int num = backTrack(s, i + 1, t, ti + 1, memo);
                memo[i][ti] = num;
                count += num;
            }
        }

        return count;
    }
}
