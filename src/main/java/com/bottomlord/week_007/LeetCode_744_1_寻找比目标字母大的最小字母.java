package com.bottomlord.week_007;

public class LeetCode_744_1_寻找比目标字母大的最小字母 {
    public char nextGreatestLetter(char[] letters, char target) {
        boolean[] bucket = new boolean[26];
        int min = 'z', max = 'a';
        for (char c : letters) {
            if (c - target == 1) {
                return c;
            }

            bucket[c - 'a'] = true;
            min = Math.min(c, min);
            max = Math.max(c, max);
        }

        if (target < min || target >= max) {
            return (char)min;
        }

        for (int i = min - 'a'; i <= max - 'a'; i++) {
            if (bucket[i] && target - 'a' < i) {
                return (char)(i + 'a');
            }
        }

        return 'a';
    }
}
