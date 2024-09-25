package com.bottomlord.week_112;

/**
 * @author chen yue
 * @date 2021-09-01 08:48:46
 */
public class LeetCode_1576_1_替换所有的问号 {
    public String modifyString(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (cs[i] != '?') {
                continue;
            }

            char pre = i - 1 >= 0 ? cs[i - 1] : ' ';
            char next = i + 1 < n ? cs[i + 1] : ' ';

            for (int j = 0; j < 26; j++) {
                char c = (char) ('a' + j);
                if (c != pre && c != next) {
                    cs[i] = c;
                    break;
                }
            }
        }

        return new String(cs);
    }
}
