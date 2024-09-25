package com.bottomlord.week_131;

/**
 * @author chen yue
 * @date 2022-01-14 09:13:02
 */
public class LeetCode_2068_1_检查两个字符串是否几乎相等 {
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] bucket = new int[26];
        for (char c : word1.toCharArray()) {
            bucket[c - 'a']++;
        }

        for (char c : word2.toCharArray()) {
            bucket[c - 'a']--;
        }

        for (int num : bucket) {
            if (Math.abs(num) > 3) {
                return false;
            }
        }

        return true;
    }
}
