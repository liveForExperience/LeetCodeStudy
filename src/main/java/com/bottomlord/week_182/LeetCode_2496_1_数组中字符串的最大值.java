package com.bottomlord.week_182;

/**
 * @author chen yue
 * @date 2023-01-07 06:33:24
 */
public class LeetCode_2496_1_数组中字符串的最大值 {
    public int maximumValue(String[] strs) {
        int ans = Integer.MIN_VALUE;
        for (String str : strs) {
            ans = Math.max(ans, getNum(str));
        }
        return ans;
    }

    private int getNum(String str) {
        int num = 0;
        char[] cs = str.toCharArray();
        for (char c : cs) {
            if (!Character.isDigit(c)) {
                return str.length();
            }

            num = num * 10 + (c - '0');
        }

        return num;
    }
}
