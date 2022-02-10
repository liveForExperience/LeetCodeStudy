package com.bottomlord.week_135;

/**
 * @author chen yue
 * @date 2022-02-10 21:19:57
 */
public class LeetCode_offerII18_1_有效的回文 {
    public boolean isPalindrome(String s) {
        char[] cs = s.toCharArray();
        int head = 0, tail = s.length() - 1;
        while (head < tail) {
            while (head < tail && !valid(cs[head])) {
                head++;
            }

            while (head < tail && !valid(cs[tail])) {
                tail--;
            }

            if (head >= tail) {
                return true;
            }

            if (toLower(cs[head]) != toLower(cs[tail])) {
                return false;
            }

            head++;
            tail--;
        }

        return true;
    }

    private boolean valid(char c) {
        return isNum(c) || isAlpha(c);
    }

    private boolean isNum(char c) {
        return c <= '9' && c >= '0';
    }

    private boolean isAlpha(char c) {
        return isLower(c) || isUpper(c);
    }

    private boolean isUpper(char c) {
        return c <= 'Z' && c >= 'A';
    }

    private boolean isLower(char c) {
        return c <= 'z' && c >= 'a';
    }

    private char toLower(char c) {
        if (isUpper(c)) {
            return (char)(c + 32);
        }

        return c;
    }
}
