package com.bottomlord.week_117;

/**
 * @author chen yue
 * @date 2021-10-06 09:46:04
 */
public class LeetCode_1768_1_交替合并字符串 {
    public String mergeAlternately(String word1, String word2) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < word1.length() || index < word2.length()) {
            if (index < word1.length()) {
                sb.append(word1.charAt(index));
            }

            if (index < word2.length()) {
                sb.append(word2.charAt(index));
            }

            index++;
        }

        return sb.toString();
    }
}
