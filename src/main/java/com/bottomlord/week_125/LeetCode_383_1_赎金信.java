package com.bottomlord.week_125;

/**
 * @author chen yue
 * @date 2021-12-04 14:14:15
 */
public class LeetCode_383_1_赎金信 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int count = 0;
        int[] bucket = new int[26];
        for (char c : ransomNote.toCharArray()) {
            bucket[c - 'a']++;
            count++;
        }

        for (char c : magazine.toCharArray()) {
            if (bucket[c - 'a'] > 0) {
                bucket[c - 'a']--;
                count--;
            }

            if (count == 0) {
                return true;
            }
        }

        return false;
    }
}
