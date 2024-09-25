package com.bottomlord.week_086;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/3/2 9:08
 */
public class LeetCode_472_1_连接词 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            if (Objects.equals("", word)) {
                continue;
            }

            set.remove(word);
            if (backTrack(word, set)) {
                ans.add(word);
            }
            set.add(word);
        }

        return ans;
    }

    private boolean backTrack(String word, Set<String> set) {
        if (word.length() == 0) {
            return true;
        }

        boolean result = false;
        for (int i = 0; i < word.length(); i++) {
            if (set.contains(word.substring(0, i + 1))) {
                result = backTrack(word.substring(i + 1), set);
            }

            if (result) {
                break;
            }
        }

        return result;
    }
}
