package com.bottomlord.week_093;

import java.util.List;

public class LeetCode_524_2 {
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
        int wi = 0, si = 0;
        while (wi < word.length() && si < s.length()) {
            if (word.charAt(wi) == s.charAt(si)) {
                wi++;
                si++;
            } else {
                si++;
            }
        }

        return wi == word.length();
    }
}
