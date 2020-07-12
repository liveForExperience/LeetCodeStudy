package com.bottomlord.week_053;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/7/9 8:19
 */
public class LeetCode_127_1_单词接龙 {
    private int ans = Integer.MAX_VALUE;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Set<String> set = new HashSet<>();
        set.add(beginWord);
        backTrack(beginWord, endWord, set, wordList, 1);
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    private void backTrack(String curWord, String endWord, Set<String> memo, List<String> wordList, int count) {
        if (count > ans) {
            return;
        }

        if (Objects.equals(curWord, endWord)) {
            ans = Math.min(ans, count);
            return;
        }

        for (String word : wordList) {
            if (isNext(curWord, word) && !memo.contains(word)) {
                memo.add(word);
                backTrack(word, endWord, memo, wordList, count + 1);
                memo.remove(word);
            }
        }
    }

    private boolean isNext(String curWord, String nextWord) {
        if (curWord.length() != nextWord.length()) {
            return false;
        }

        int count = 0;
        for (int i = 0; i < curWord.length(); i++) {
            if (curWord.charAt(i) != nextWord.charAt(i)) {
                count++;
            }

            if (count > 1) {
                return false;
            }
        }

        return count == 1;
    }
}
