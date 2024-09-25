package com.bottomlord.week_138;

/**
 * @author chen yue
 * @date 2022-03-03 18:46:13
 */
public class LeetCode_2185_1_统计包含给定前缀的字符串 {
    public int prefixCount(String[] words, String pref) {
        int ans = 0;
        for (String word : words) {
            if (match(word, pref)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean match(String word, String pref) {
        int len = pref.length();
        if (len > word.length()) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            if (word.charAt(i) != pref.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
