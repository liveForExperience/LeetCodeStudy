package com.bottomlord.week_164;

/**
 * @author chen yue
 * @date 2022-09-03 14:39:58
 */
public class LeetCode_2351_1_第一个出现两次的字母 {
    public char repeatedCharacter(String s) {
        int[] bucket = new int[26];
        char[] cs = s.toCharArray();
        for (char c : cs) {
            bucket[c - 'a']++;

            if (bucket[c - 'a'] == 2) {
                return c;
            }
        }

        return ' ';
    }
}
