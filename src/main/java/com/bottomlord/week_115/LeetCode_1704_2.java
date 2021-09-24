package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-24 08:30:09
 */
public class LeetCode_1704_2 {
    public boolean halvesAreAlike(String s) {
        int[] memo = new int['z' + 1];
        for (char c : new char[]{'a', 'e', 'i', 'o', 'u'}) {
            memo[c] = memo[c - 32] = 1;
        }

        return count(0, s.length() / 2, s, memo) ==
               count(s.length() / 2, s.length(), s, memo);
    }

    private int count(int start, int end, String str, int[] memo) {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (memo[str.charAt(i)] == 1) {
                count++;
            }
        }
        return count;
    }
}
