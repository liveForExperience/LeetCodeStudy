package com.bottomlord.week_072;

/**
 * @author ChenYue
 * @date 2020/11/25 8:23
 */
public class LeetCode_1370_1_上升下降字符串 {
    public String sortString(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        while (sb.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    sb.append((char)(i + 'a'));
                    count[i]--;
                }
            }

            for (int i = 25; i >= 0; i--) {
                if (count[i] > 0) {
                    sb.append((char)(i + 'a'));
                    count[i]--;
                }
            }
        }

        return sb.toString();
    }
}
