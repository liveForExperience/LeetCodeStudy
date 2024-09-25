package com.bottomlord.week_093;

import java.util.List;

public class LeetCode_524_1_通过删除字母匹配到字典里最长单词 {
    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((x, y) -> {
            if (x.length() != y.length()) {
                return y.length() - x.length();
            }
            return x.compareTo(y);
        });

        for (String word : dictionary) {
            int wi = 0, si = 0;
            while (wi < word.length() && si < s.length()) {
                if (word.charAt(wi) == s.charAt(si)) {
                    wi++;
                    si++;
                } else {
                    si++;
                }
            }

            if (wi == word.length()) {
                return word;
            }
        }

        return "";
    }
}
