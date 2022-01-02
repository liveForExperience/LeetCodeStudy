package com.bottomlord.week_129;

/**
 * @author chen yue
 * @date 2021-12-31 09:06:04
 */
public class LeetCode_1967_1_作为子字符串出现在单词中的字符串数目 {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        for (String pattern : patterns) {
            count += word.contains(pattern) ? 0 : 1;
        }
        return count;
    }
}
