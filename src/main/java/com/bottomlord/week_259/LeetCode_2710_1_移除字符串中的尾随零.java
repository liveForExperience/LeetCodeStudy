package com.bottomlord.week_259;

/**
 * @author chen yue
 * @date 2024-06-29 16:58:31
 */
public class LeetCode_2710_1_移除字符串中的尾随零 {
    public String removeTrailingZeros(String num) {
        char[] cs = num.toCharArray();
        int n = cs.length, len = 0, index = n - 1;
        while (index >= 0 && cs[index] == '0') {
            index--;
            len++;
        }

        return num.substring(0, n - len);
    }
}
