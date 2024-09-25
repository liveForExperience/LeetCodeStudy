package com.bottomlord.week_049;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/6/10 8:27
 */
public class LeetCode_30_2 {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || s.length() == 0) {
            return Collections.emptyList();
        }

        int wordsNum = words.length;
        if (wordsNum == 0) {
            return Collections.emptyList();
        }

        int wordLen = words[0].length();

        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        Map<String, Integer> memo = new HashMap<>();
        for (int i = 0; i < wordLen; i++) {
            int start = i, index = i;
            while (start < s.length() - wordLen * wordsNum + 1) {
                while (index < start + wordLen * wordsNum) {
                    String word = s.substring(index, index + wordLen);
                    memo.put(word, memo.getOrDefault(word, 0) + 1);
                    index += wordLen;
                    if (!map.containsKey(word)) {
                        start = index;
                        memo.clear();
                        break;
                    }

                    if (memo.get(word) > map.get(word)) {
                        while (memo.get(word) > map.get(word)) {
                            String headWord = s.substring(start, start + wordLen);
                            memo.put(headWord, memo.get(headWord) - 1);
                            start += wordLen;
                        }
                        break;
                    }
                }

                if (index == start + wordLen * wordsNum) {
                    ans.add(start);
                    String word = s.substring(start, start + wordLen);
                    memo.put(word, memo.get(word) - 1);
                    start += wordLen;
                }
            }
        }

        return ans;
    }
}
