package com.bottomlord.week_118;

/**
 * @author chen yue
 * @date 2021-10-13 22:46:23
 */
public class LeetCode_1796_1_字符串中第二大的数字 {
    public int secondHighest(String s) {
        char[] cs = s.toCharArray();
        Integer one = null, two = null;
        for (char c : cs) {
            if (!Character.isDigit(c)) {
                continue;
            }

            int num = c - '0';
            if (one == null) {
                one = num;
                continue;
            }

            if (num > one) {
                two = one;
                one = num;
                continue;
            }

            if (num < one && (two == null || num > two)) {
                two = num;
            }
        }

        return two == null ? -1 : two;
    }
}
