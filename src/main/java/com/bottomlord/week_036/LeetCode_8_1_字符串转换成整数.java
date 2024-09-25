package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/12 8:47
 */
public class LeetCode_8_1_字符串转换成整数 {
    public int myAtoi(String str) {
        int ans = 0, i = 0, len = str.length();
        boolean positive = true;

        while (i < len && str.charAt(i) == ' ') {
            i++;
        }

        if (i < len && str.charAt(i) == '-') {
            positive = false;
        }

        if (i < len && (str.charAt(i) == '-' || str.charAt(i) == '+')) {
            i++;
        }

        while (i < len && Character.isDigit(str.charAt(i))) {
            int num = str.charAt(i) - '0';
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && num > 7)) {
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            ans = ans * 10 + num;
            i++;
        }

        return positive ? ans : -ans;
    }
}
