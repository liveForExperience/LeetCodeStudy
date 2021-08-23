package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-23 08:49:46
 */
public class LeetCode_1528_1_重新排列字符串 {
    public String restoreString(String s, int[] indices) {
        char[] cs = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            cs[indices[i]] = s.charAt(i);
        }
        return new String(cs);
    }
}
