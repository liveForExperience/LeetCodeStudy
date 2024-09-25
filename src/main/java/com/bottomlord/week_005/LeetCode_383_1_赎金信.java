package com.bottomlord.week_005;

public class LeetCode_383_1_赎金信 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] bucket = new int[128];
        for (char c : magazine.toCharArray()) {
            bucket[c]++;
        }

        for (char c : ransomNote.toCharArray()) {
            if (bucket[c] == 0) {
                return false;
            }

            bucket[c]--;
        }

        return true;
    }
}
