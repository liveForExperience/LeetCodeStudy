package com.bottomlord.week_083;

/**
 * @author ChenYue
 * @date 2021/2/10 8:38
 */
public class LeetCode_567_1_字符串的排列 {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        int[] count1 = new int[26], count2 = new int[26];

        for (char c : s1.toCharArray()) {
            count1[c - 'a']++;
        }

        for (int i = 0; i < len1; i++) {
            count2[s2.charAt(i) - 'a']++;
        }

        for (int i = len1 - 1; i < len2; i++) {
            if (fit(count1, count2)) {
                return true;
            }

            count2[s2.charAt(i + 1)]++;
            count2[s2.charAt(i - len1 + 1)]--;
        }

        return false;
    }

    private boolean fit(int[] c1, int[] c2) {
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }

        return true;
    }
}
