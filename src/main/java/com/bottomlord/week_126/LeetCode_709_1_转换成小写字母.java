package com.bottomlord.week_126;

/**
 * @author chen yue
 * @date 2021-12-12 15:16:12
 */
public class LeetCode_709_1_转换成小写字母 {
    public String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (Character.isUpperCase(c)) {
                sb.append((char)(c - 'A' + 'a'));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
