package com.bottomlord.week_086;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/3/3 10:26
 */
public class LeetCode_472_2 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>(Arrays.asList(words)), memo = new HashSet<>();
        for (String word : words) {
            if (Objects.equals("", word)) {
                continue;
            }

            set.remove(word);
            if (backTrack(word, set, memo)) {
                ans.add(word);
            }
            set.add(word);
        }

        return ans;
    }

    private boolean backTrack(String word, Set<String> set, Set<String> memo) {
        if (word.length() == 0) {
            return true;
        }

        boolean result = false;
        for (int i = 1; i <= word.length(); i++) {
            if (set.contains(word.substring(0, i))) {
                String right = word.substring(i);
                if (memo.contains(right)) {
                    continue;
                }

                if (backTrack(word.substring(i), set, memo)) {
                    return true;
                }

                memo.add(right);
            }
        }

        return result;
    }
}
