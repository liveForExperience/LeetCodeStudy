package com.bottomlord.week_144;

/**
 * @author chen yue
 * @date 2022-04-12 08:54:49
 */
public class LeetCode_806_1_写字符串需要的行数 {
    public int[] numberOfLines(int[] widths, String S) {
        char[] cs = S.toCharArray();
        int line = 1, count = 0;

        for (char c : cs) {
            count += widths[c - 'a'];
            if (count > 100) {
                count = widths[c - 'a'];
                line++;
            }
        }

        return new int[]{line, count};
    }
}