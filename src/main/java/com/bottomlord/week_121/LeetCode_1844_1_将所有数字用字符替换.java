package com.bottomlord.week_121;

/**
 * @author chen yue
 * @date 2021-11-06 14:45:28
 */
public class LeetCode_1844_1_将所有数字用字符替换 {
    public String replaceDigits(String s) {
        char[] cs = s.toCharArray();
        for (int i = 1; i < cs.length; i += 2) {
            cs[i] = (char)(cs[i - 1] + (cs[i] - '0'));
        }
        return new String(cs);
    }
}
