package com.bottomlord.week_130;

/**
 * @author chen yue
 * @date 2022-01-05 19:37:48
 */
public class LeetCode_2000_1_反转单词前缀 {
    public String reversePrefix(String word, char ch) {
        int index = findIndex(word, ch);
        if (index == -1) {
            return word;
        }

        char[] cs = word.toCharArray();
        int n = index / 2;
        for (int i = 0; i <= n; i++) {
            char c = cs[i];
            cs[i] = cs[index - i];
            cs[index - i] = c;
        }

        return new String(cs);
    }

    private int findIndex(String word, char c) {
        char[] cs = word.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == c) {
                return i;
            }
        }

        return -1;
    }
}
