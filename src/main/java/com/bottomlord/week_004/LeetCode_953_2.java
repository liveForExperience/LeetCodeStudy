package com.bottomlord.week_004;

/**
 * @author LiveForExperience
 * @date 2019/8/1 13:12
 */
public class LeetCode_953_2 {
    public boolean isAlienSorted(String[] words, String order) {
        int[] dic = new int[26];
        for (int i = 0; i < 26; i++) {
            dic[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (!judge(words[i], words[i + 1], dic)) {
                return false;
            }
        }
        return true;
    }

    private boolean judge(String s1, String s2, int[] dic) {
        int minLen = s1.length() <= s2.length() ? s1.length() : s2.length();
        for (int i = 0; i < minLen; i++) {
            if (dic[s1.charAt(i) - 'a'] < dic[s2.charAt(i) - 'a']) {
                return true;
            }
            if (dic[s1.charAt(i) - 'a'] > dic[s2.charAt(i) - 'a']) {
                return false;
            }
        }
        return s1.length() <= s2.length();
    }
}
