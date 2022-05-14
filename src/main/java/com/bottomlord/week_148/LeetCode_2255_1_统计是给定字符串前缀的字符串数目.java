package com.bottomlord.week_148;

/**
 * @author chen yue
 * @date 2022-05-14 09:48:27
 */
public class LeetCode_2255_1_统计是给定字符串前缀的字符串数目 {
    public int countPrefixes(String[] words, String s) {
        int count = 0;
        for (String word : words) {
            if (s.startsWith(word)) {
                count++;
            }
        }
        return count;
    }
}
