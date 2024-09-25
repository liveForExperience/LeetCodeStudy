package com.bottomlord.week_113;

/**
 * @author chen yue
 * @date 2021-09-12 20:16:41
 */
public class LeetCode_678_4 {
    public boolean checkValidString(String s) {
        int min = 0, max = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                min++;
                max++;
            } else if (c == ')') {
                if (min > 0) {
                    min--;
                }
                max--;
            } else if (c == '*') {
                if (min > 0) {
                    min--;
                }
                max++;
            }

            if (max < 0) {
                return false;
            }
        }

        return min == 0;
    }
}
