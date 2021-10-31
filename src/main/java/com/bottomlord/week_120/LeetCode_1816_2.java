package com.bottomlord.week_120;

/**
 * @author chen yue
 * @date 2021-10-31 21:07:46
 */
public class LeetCode_1816_2 {
    public String truncateSentence(String s, int k) {
        int count = 0, i = 0;
        for (; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                count++;
            }

            if (count == k) {
                break;
            }
        }

        return s.substring(i);
    }
}