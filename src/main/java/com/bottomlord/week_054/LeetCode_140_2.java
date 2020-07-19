package com.bottomlord.week_054;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/7/17 8:51
 */
public class LeetCode_140_2 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return backTrack(s, new HashSet<>(wordDict), 0, new HashMap<>());
    }

    private List<String> backTrack(String s, Set<String> words, int index, Map<Integer, List<String>> memo) {
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        List<String> ans = new ArrayList<>();
        if (index == s.length()) {
            ans.add("");
        }

        for (int i = index + 1; i <= s.length(); i++) {
            String word = s.substring(index, i);
            if (words.contains(word)) {
                List<String> list = backTrack(s, words, i, memo);
                for (String str : list) {
                    ans.add(s.substring(index, i) + (Objects.equals(str, "") ? "" : " ") + str);
                }
            }
        }

        memo.put(index, ans);
        return ans;
    }
}