package com.bottomlord.contest_20220619;

/**
 * @author chen yue
 * @date 2022-06-19 10:17:57
 */
public class Contest_1_1_兼具大小写的最好英文单词 {
    public String greatestLetter(String s) {
        int[] arr = new int[26];
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (Character.isUpperCase(c)) {
                arr[c - 'A'] |= 1;
            }

            if (Character.isLowerCase(c)) {
                arr[c - 'a'] |= 1 << 1;
            }
        }

        String ans = "";

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 3) {
                ans = "" + (char)(i + 'A');
            }
        }

        return ans;
    }
}
