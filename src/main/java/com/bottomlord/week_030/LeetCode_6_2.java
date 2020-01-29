package com.bottomlord.week_030;

/**
 * @author ThinkPad
 * @date 2020/1/29 15:46
 */
public class LeetCode_6_2 {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        StringBuilder ans = new StringBuilder();
        int len = s.length(), cycle = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; i + j < len; j += cycle) {
                ans.append(s.charAt(i + j));
                if (i != 0 && i != numRows - 1 && j + cycle - i < len) {
                    ans.append(s.charAt(j + cycle - i));
                }
            }
        }
        return ans.toString();
    }
}