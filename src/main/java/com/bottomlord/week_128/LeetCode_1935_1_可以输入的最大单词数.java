package com.bottomlord.week_128;

/**
 * @author chen yue
 * @date 2021-12-20 20:16:55
 */
public class LeetCode_1935_1_可以输入的最大单词数 {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] arr = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            arr[c - 'a'] = true;
        }

        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            boolean flag = false;
            while (i < text.length() && text.charAt(i) != ' ') {
                if (arr[text.charAt(i) - 'a']) {
                    flag = true;
                }

                i++;
            }

            if (!flag) {
                count++;
            }
        }

        return count;
    }
}
