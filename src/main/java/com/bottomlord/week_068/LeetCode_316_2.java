package com.bottomlord.week_068;

/**
 * @author ChenYue
 * @date 2020/10/27 8:37
 */
public class LeetCode_316_2 {
    public String removeDuplicateLetters(String s) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int index = 0;
        for (int i = 0; i < len; i++) {
            index = s.charAt(index) <= s.charAt(i) ? index : i;
            if (--count[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }

        return s.charAt(index) + removeDuplicateLetters(s.substring(index + 1).replaceAll("" + s.charAt(index), ""));
    }
}
