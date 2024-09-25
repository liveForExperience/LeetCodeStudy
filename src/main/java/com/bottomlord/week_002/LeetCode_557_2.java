package com.bottomlord.week_002;

/**
 * @author LiveForExperience
 * @date 2019/7/17 12:35
 */
public class LeetCode_557_2 {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.append(" ");

        char[] cs = sb.toString().toCharArray();

        int left = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] != ' ') {
                continue;
            }

            int right = i - 1;
            while (left < right) {
                cs[left] ^= cs[right];
                cs[right] ^= cs[left];
                cs[left++] ^= cs[right--];
            }

            left = i + 1;
        }

        return new String(cs).trim();
    }
}
