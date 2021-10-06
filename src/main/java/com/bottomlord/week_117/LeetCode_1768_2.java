package com.bottomlord.week_117;

/**
 * @author chen yue
 * @date 2021-10-06 09:50:56
 */
public class LeetCode_1768_2 {
    public String mergeAlternately(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        char[] cs = new char[len1 + len2];
        int i = 0, c = 0;

        while (i < len1 || i < len2) {
            if (i < len1) {
                cs[c++] = word1.charAt(i);
            }

            if (i < len2) {
                cs[c++] = word2.charAt(i);
            }

            i++;
        }

        return new String(cs);
    }
}
