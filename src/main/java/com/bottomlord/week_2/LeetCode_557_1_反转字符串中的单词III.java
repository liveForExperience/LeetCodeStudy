package com.bottomlord.week_2;

/**
 * @author LiveForExperience
 * @date 2019/7/16 21:19
 */
public class LeetCode_557_1_反转字符串中的单词III {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        for (String word: s.split("")) {
            sb.append(reverse(word)).append(" ");
        }
        return sb.toString().trim();
    }

    private String reverse(String word) {
        char[] cs = word.toCharArray();
        int left = 0, right = cs.length;
        while (left < right) {
            cs[left] ^= cs[right];
            cs[right] ^= cs[left];
            cs[left++] ^= cs[right--];
        }
        return new String(cs);
    }
}
