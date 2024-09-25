package com.bottomlord.week_132;

/**
 * @author chen yue
 * @date 2022-01-22 18:06:46
 */
public class LeetCode_2108_1_找出数组中的第一个回文字符串 {
    public String firstPalindrome(String[] words) {
        for (String word : words) {
            if (isPalindrome(word)) {
                return word;
            }
        }
        return "";
    }

    private boolean isPalindrome(String word) {
        int start = 0, end = word.length() - 1;
        while (start < end) {
            if (word.charAt(start++) != word.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
