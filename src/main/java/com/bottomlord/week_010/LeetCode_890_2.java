package com.bottomlord.week_010;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_890_2 {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (isPattern(word, pattern) && isPattern(pattern, word)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private boolean isPattern(String a, String b) {
        char[] cs = a.toCharArray();
        char[] bucket = new char[58];
        for (int i = 0; i < a.length(); i++) {
            int index = cs[i] - 65;
            if (bucket[index] == 0) {
                bucket[index] = b.charAt(i);
            } else if (bucket[index] != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}