package com.bottomlord.week_199;

/**
 * @author chen yue
 * @date 2023-05-03 11:55:13
 */
public class LeetCode_1003_3 {
    public boolean isValid(String s) {
        int i = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c == 'c') {
                if (i < 2 || cs[--i] != 'b' || cs[--i] != 'a') {
                    return false;
                }
            } else {
                cs[i++] = c;
            }
        }

        return i == 0;
    }

    public static void main(String[] args) {
        LeetCode_1003_3 t = new LeetCode_1003_3();
        t.isValid("aabcbc");
    }
}
