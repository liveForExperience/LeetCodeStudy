package com.bottomlord.week_053;

/**
 * @author ChenYue
 * @date 2020/7/7 8:25
 */
public class LeetCode_115_1_不同的子序列 {
    public int numDistinct(String s, String t) {
        return backTrack(s, 0, t, 0);
    }

    private int backTrack(String s, int si, String t, int ti) {
        if (si == s.length() && ti != t.length()) {
            return 0;
        }

        if (ti == t.length()) {
            return 1;
        }

        int count = 0;
        for (int i = si; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(ti)) {
                count += backTrack(s, i + 1, t, ti + 1);
            }
        }

        return count;
    }
}
