package com.bottomlord.week_202;

/**
 * @author chen yue
 * @date 2023-05-23 15:28:42
 */
public class LeetCode_2696_1_删除子串后的字符串最小长度 {
    public int minLength(String s) {
        final String ab = "AB", cd = "CD";
        while (s.indexOf(ab) != -1 || s.indexOf(cd) != -1) {
            if (s.indexOf(ab) != -1) {
                s = s.substring(0, s.indexOf(ab)) + s.substring(s.indexOf(ab) + 2);
            } else {
                s = s.substring(0, s.indexOf(cd)) + s.substring(s.indexOf(cd) + 2);
            }
        }

        return s.length();
    }
}
