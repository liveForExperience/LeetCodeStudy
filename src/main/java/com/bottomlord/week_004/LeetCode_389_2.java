package com.bottomlord.week_004;

/**
 * @author LiveForExperience
 * @date 2019/7/29 22:29
 */
public class LeetCode_389_2 {
    public char findTheDifference(String s, String t) {
        int[] dict = new int[26];

        for (char c : s.toCharArray()) {
            dict[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            if (dict[c - 'a'] == 0) {
                return c;
            }
        }

        return ' ';
    }
}