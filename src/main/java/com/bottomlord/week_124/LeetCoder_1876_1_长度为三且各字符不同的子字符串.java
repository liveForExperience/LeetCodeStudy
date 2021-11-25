package com.bottomlord.week_124;

/**
 * @author chen yue
 * @date 2021-11-24 19:14:08
 */
public class LeetCoder_1876_1_长度为三且各字符不同的子字符串 {
    public int countGoodSubstrings(String s) {
        char[] cs = s.toCharArray();
        int count = 0;
        for (int i = 0; i < s.length() - 2; i++) {
            if (cs[i] != cs[i + 1] && cs[i + 1] != cs[i + 2] && cs[i] != cs[i + 2]) {
                count++;
            }
        }
        return count;
    }
}
