package com.bottomlord.week_130;

/**
 * @author chen yue
 * @date 2022-01-05 08:46:56
 */
public class LeetCode_1576_1_替换所有的问号 {
    public String modifyString(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        if (cs.length == 1) {
            if (cs[0] == '?') {
                return "a";
            } else {
                return s;
            }
        }

        if (cs[0] == '?') {
            if (cs[1] == 'a') {
                cs[0] = 'b';
            } else {
                cs[0] = 'a';
            }
        }

        if (cs[n - 1] == '?') {
            if (cs[n - 2] == 'a') {
                cs[n - 1] = 'b';
            } else {
                cs[n - 1] = 'a';
            }
        }

        for (int i = 1; i < n - 1; i++) {
            if (cs[i] != '?') {
                continue;
            }
            cs[i] = pick(cs[i - 1], cs[i + 1]);
        }

        return new String(cs);
    }

    private char pick(char pre, char post) {
        for (int i = 0; i < 26; i++) {
            if (pre - 'a' != i && post - 'a' != i) {
                return (char)(i + 'a');
            }
        }
        return 'a';
    }
}
