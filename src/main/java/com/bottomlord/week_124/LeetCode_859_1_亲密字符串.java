package com.bottomlord.week_124;

/**
 * @author chen yue
 * @date 2021-11-23 08:41:18
 */
public class LeetCode_859_1_亲密字符串 {
    public boolean buddyStrings(String s, String goal) {
        int len = s.length(), count = 0;
        if (goal.length() != len) {
            return false;
        }

        char d1 = ' ', d2 = ' ';
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                count++;

                if (count > 2) {
                    return false;
                }

                if (count == 1) {
                    d1 = s.charAt(i);
                    d2 = goal.charAt(i);
                    continue;
                }

                if (s.charAt(i) != d2 || goal.charAt(i) != d1) {
                    return false;
                }
            }
        }

        if (count == 1) {
            return false;
        }

        if (count == 0) {
            int[] bucket = new int[26];
            for (char c : goal.toCharArray()) {
                bucket[c - 'a']++;
            }
            for (int i : bucket) {
                if (i >= 2) {
                    return true;
                }
            }

            return false;
        }

        return true;
    }
}
