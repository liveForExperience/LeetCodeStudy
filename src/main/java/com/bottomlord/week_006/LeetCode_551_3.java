package com.bottomlord.week_006;

public class LeetCode_551_3 {
    public boolean checkRecord(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') {
                if (count++ == 1) {
                    return false;
                }
            }
        }

        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == 'L' && s.charAt(i + 1) == 'L' && s.charAt(i + 2) == 'L') {
                return false;
            }
        }

        return true;
    }
}