package com.bottomlord.week_117;

/**
 * @author chen yue
 * @date 2021-10-07 10:39:23
 */
public class LeetCode_434_1_字符串中的单词数 {
    public int countSegments(String s) {
        int n = s.length(), count = 0;
        char[] cs = s.toCharArray();

        for (int i = 0; i < n; i++) {
            int start = i;
            while (i < n && cs[i] != ' ') {
                i++;
            }

            if (i != start) {
                count++;
            }
        }

        return count;
    }
}
