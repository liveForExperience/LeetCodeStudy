package com.bottomlord.week_258;

/**
 * @author chen yue
 * @date 2024-06-23 19:25:12
 */
public class LeetCode_520_1_检测大写字母 {
    public boolean detectCapitalUse(String word) {
        int n = word.length(), cnt = 0;
        char[] cs = word.toCharArray();
        for (char c : cs) {
            if (isUpper(c)) {
                cnt++;
            }
        }

        return n == cnt || cnt == 0 || cnt == 1 && isUpper(cs[0]);
    }

    private boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }
}
