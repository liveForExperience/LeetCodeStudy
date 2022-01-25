package com.bottomlord.week_133;

/**
 * @author chen yue
 * @date 2022-01-25 08:55:16
 */
public class LeetCode_2124_1_检查是否所有A都在B之前 {
    public boolean checkString(String s) {
        char[] cs = s.toCharArray();
        boolean half = false;
        for (char c : cs) {
            if (!half && c == 'b') {
                half = true;
                continue;
            }

            if (half && c == 'a') {
                return false;
            }
        }

        return true;
    }
}
