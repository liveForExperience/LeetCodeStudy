package com.bottomlord.week_269;

/**
 * @author chen yue
 * @date 2024-09-05 09:08:48
 */
public class LeetCode_3174_1_清除数字 {
    public String clearDigits(String s) {
        char[] cs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : cs) {
            if (Character.isDigit(c)) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
