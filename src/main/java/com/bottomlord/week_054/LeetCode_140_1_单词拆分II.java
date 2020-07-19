package com.bottomlord.week_054;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/7/17 8:28
 */
public class LeetCode_140_1_单词拆分II {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        List<String> ans = new ArrayList<>();
        backTrack(s, 0, words, new StringBuilder(), ans);
        return ans;
    }

    private void backTrack(String s, int index, Set<String> words, StringBuilder path, List<String> ans) {
        if (index == s.length()) {
            ans.add(path.toString().trim());
            return;
        }

        for (int i = index + 1; i <= s.length(); i++) {
            String word = s.substring(index, i);
            if (words.contains(word)) {
                int len = path.length();
                path.append(" ").append(word);
                backTrack(s, i, words, path, ans);
                path.delete(len, path.length());
            }
        }
    }
}
