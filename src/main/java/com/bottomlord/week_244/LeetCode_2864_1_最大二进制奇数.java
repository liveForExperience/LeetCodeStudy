package com.bottomlord.week_244;

/**
 * @author chen yue
 * @date 2024-03-13 14:33:42
 */
public class LeetCode_2864_1_最大二进制奇数 {
    public String maximumOddBinaryNumber(String s) {
        char[] cs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : cs) {
            if (c == '1') {
                sb.append('1');
            }
        }

        int z = s.length() - sb.length();
        sb.setLength(sb.length() - 1);
        for (int i = 0; i < z; i++) {
            sb.append('0');
        }

        return sb.append('1').toString();
    }
}
