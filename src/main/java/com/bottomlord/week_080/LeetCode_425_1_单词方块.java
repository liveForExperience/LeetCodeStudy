package com.bottomlord.week_080;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/1/18 8:26
 */
public class LeetCode_425_1_单词方块 {
    public List<List<String>> wordSquares(String[] words) {
        int len = words[0].length();
        List<List<String>> ans = new ArrayList<>();
        for (String word : words) {
            backTrack(1, len, words, new ArrayList<String>(){{this.add(word);}}, ans);
        }
        return ans;
    }

    private void backTrack(int index, int len, String[] words, List<String> wordList, List<List<String>> ans) {
        if (index == len) {
            ans.add(new ArrayList<>(wordList));
            return;
        }

        StringBuilder prefixSb = new StringBuilder();
        for (String word : wordList) {
            prefixSb.append(word.charAt(index));
        }
        String prefix = prefixSb.toString();

        for (String word : words) {
            if (word.startsWith(prefix)) {
                wordList.add(word);
                backTrack(index + 1, len, words, wordList, ans);
                wordList.remove(wordList.size() - 1);
            }
        }
    }
}
