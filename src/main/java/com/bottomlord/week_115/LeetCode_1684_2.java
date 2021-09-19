package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-19 21:57:05
 */
public class LeetCode_1684_2 {
    public int countConsistentStrings(String allowed, String[] words) {
        int mask = 0;
        for (char c : allowed.toCharArray()) {
            int bit = c - '0';
            mask |= 1 << bit;
        }

        int count = 0;
        for (String word : words) {
            boolean flag = true;
            for (char c : word.toCharArray()) {
                int bit = c - '0';
                if (((1 << bit) | mask) != mask) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }

        return count;
    }
}
