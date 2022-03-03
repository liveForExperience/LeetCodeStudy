package com.bottomlord.week_138;

/**
 * @author chen yue
 * @date 2022-03-01 09:14:12
 */
public class LeetCode_6_1_Z字形变换 {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        int cycle = 2 * numRows - 2, len = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; i + j < len; j += cycle) {
                sb.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycle - i < len) {
                    sb.append(s.charAt(j + cycle - i));
                }
            }
        }
        return sb.toString();
    }
}
