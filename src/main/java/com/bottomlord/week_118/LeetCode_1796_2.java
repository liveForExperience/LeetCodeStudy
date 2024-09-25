package com.bottomlord.week_118;

/**
 * @author chen yue
 * @date 2021-10-13 22:57:33
 */
public class LeetCode_1796_2 {
    public int secondHighest(String s) {
        boolean[] bucket = new boolean[10];
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                bucket[s.charAt(i) - '0'] = true;
            }
        }

        int count = 0;
        for (int i = 9; i >= 0; i--) {
            if (bucket[i]) {
                count++;
            }

            if (count == 2) {
                return i;
            }
        }

        return -1;
    }
}
