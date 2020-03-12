package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/12 8:45
 */
public class Interview_67_1_把字符串转换成整数 {
    public int strToInt(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }

        int ans = 0, i = 0, flag = 1;
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }

        if (i < str.length() && str.charAt(i) == '-') {
            flag = -1;
        }

        if (i < str.length() && (str.charAt(i) == '-' || str.charAt(i) == '+')) {
            i++;
        }

        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            int num = str.charAt(i) - '0';
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && num > 7)) {
                return flag > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            ans = ans * 10 + num;
            i++;
        }

        return flag > 0 ? ans : -ans;
    }
}
