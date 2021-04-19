package com.bottomlord.week_093;

import java.util.List;

public class LeetCode_524_3 {
    public String findLongestWord(String s, List<String> dictionary) {
        String ans = "";
        for (String word : dictionary) {
            if (isSubSequence(s, word)) {
                if (word.length() > ans.length() || word.length() == ans.length() && word.compareTo(ans) < 0) {
                    ans = word;
                }
            }
        }

        return ans;
    }

    private boolean isSubSequence(String s, String word) {
        int index = -1;
        for (int i = 0; i < word.length(); i++) {
            index = s.indexOf(word.charAt(i), index + 1);

            if (index == -1) {
                return false;
            }
        }

        return true;
    }
}
