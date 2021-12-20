package com.bottomlord.week_128;

/**
 * @author chen yue
 * @date 2021-12-20 20:25:15
 */
public class LeetCode_1935_2 {
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] strs = text.split(" ");
        boolean[] arr = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            arr[c - 'a'] = true;
        }

        int count = 0;
        for (String str : strs) {
            if (canType(str, arr)) {
                count++;
            }
        }

        return count;
    }

    private boolean canType(String str, boolean[] arr) {
        for (char c : str.toCharArray()) {
            if (arr[c - 'a']) {
                return false;
            }
        }

        return true;
    }
}