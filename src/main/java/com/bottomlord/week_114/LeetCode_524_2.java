package com.bottomlord.week_114;

import java.util.Arrays;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-09-14 08:51:55
 */
public class LeetCode_524_2 {
    public String findLongestWord(String s, List<String> dictionary) {
        int length = s.length();
        int[][] next = new int[length + 1][];

        int[] lastArr = new int[26];
        Arrays.fill(lastArr,-1);
        next[length] = lastArr;

        for (int i = length - 1; i >= 0; i--) {
            int[] currentArr = new int[26];
            System.arraycopy(lastArr, 0, currentArr, 0, 26);
            currentArr[s.charAt(i) - 'a'] = i;
            next[i] = lastArr = currentArr;
        }

        String result = "";
        for (String word : dictionary) {
            if (isSubsequence(word, next)) {
                if (word.length() > result.length()) result = word;
                else if (word.length() == result.length() && word.compareTo(result) < 0) result = word;
            }
        }
        return result;
    }

    private boolean isSubsequence(String word, int[][] next) {
        int p = 0;
        for (int i = 0; i < word.length(); i++) {
            p = next[p][word.charAt(i) - 'a'];
            if (p == -1) return false;
            p++;
        }
        return true;
    }
}
