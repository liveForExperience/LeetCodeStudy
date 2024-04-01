package com.bottomlord.week_247;

/**
 * @author chen yue
 * @date 2024-04-01 08:50:01
 */
public class LeetCode_2810_1_故障键盘 {
    public String finalString(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c == 'i') {
                sb.reverse();
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
