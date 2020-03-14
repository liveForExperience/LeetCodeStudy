package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/14 14:59
 */
public class Interview_0103_2 {
    public String replaceSpaces(String S, int length) {
        char[] cs = new char[S.length()], s = S.substring(0, length).toCharArray();
        int ci = cs.length - 1, si = length - 1;
        while (si > 0) {
            if (s[si] == ' ') {
                cs[ci--] = '0';
                cs[ci--] = '2';
                cs[ci--] = '%';
            } else {
                cs[ci--] = s[si];
            }

            si--;
        }

        return String.valueOf(cs, ci + 1, cs.length - ci + 1);
    }
}