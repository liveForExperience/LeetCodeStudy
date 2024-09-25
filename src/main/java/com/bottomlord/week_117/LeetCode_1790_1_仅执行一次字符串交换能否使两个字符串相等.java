package com.bottomlord.week_117;

/**
 * @author chen yue
 * @date 2021-10-10 11:08:01
 */
public class LeetCode_1790_1_仅执行一次字符串交换能否使两个字符串相等 {
    public boolean areAlmostEqual(String s1, String s2) {
        int[] bucket = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            bucket[s1.charAt(i) - 'a']++;
            bucket[s2.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (bucket[i] != 0) {
                return false;
            }
        }

        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }

        return count == 0 || count == 2;
    }
}
