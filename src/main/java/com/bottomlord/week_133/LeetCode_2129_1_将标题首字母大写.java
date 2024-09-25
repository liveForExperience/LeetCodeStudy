package com.bottomlord.week_133;

/**
 * @author chen yue
 * @date 2022-01-25 08:59:35
 */
public class LeetCode_2129_1_将标题首字母大写 {
    public String capitalizeTitle(String title) {
        char[] cs = title.toCharArray();

        int j = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == ' ' || cs[i] == '\0') {
                if (i - j > 2) {
                    cs[j] = toUpper(cs[j]);
                }
                j = i + 1;
                continue;
            }

            cs[i] = toLower(cs[i]);
        }

        if (cs.length - j > 2) {
            cs[j] = toUpper(cs[j]);
        }

        return new String(cs);
    }

    private boolean isUpper(char c) {
        return c >= 65 && c <= 90;
    }

    private boolean isLower(char c) {
        return c >= 97 && c <= 122;
    }

    private char toUpper(char c) {
        return isUpper(c) ? c : (char) (c - 32);
    }

    private char toLower(char c) {
        return isLower(c) ? c : (char) (c + 32);
    }
}
