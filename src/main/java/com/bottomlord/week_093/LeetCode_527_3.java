package com.bottomlord.week_093;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/4/20 14:37
 */
public class LeetCode_527_3 {
    public List<String> wordsAbbreviation(List<String> dict) {
        Map<String, List<IndexedWord>> map = new HashMap<>();
        for (int i = 0; i < dict.size(); i++) {
            String word = dict.get(i);
            String abb = abbreviateWord(word, 0);
            List<IndexedWord> words = map.getOrDefault(abb, new ArrayList<>());
            words.add(new IndexedWord(word, i));
            map.put(abb, words);
        }

        String[] ansArr = new String[dict.size()];
        for (List<IndexedWord> indexedWords : map.values()) {
            indexedWords.sort(Comparator.comparing(x -> x.word));

            int len = indexedWords.size();

            int[] commonPrefixLens = new int[len];
            for (int i = 1; i < len; i++) {
                IndexedWord x = indexedWords.get(i), y = indexedWords.get(i - 1);
                int commonPrefixLen = longestCommonPrefix(x.word, y.word);
                commonPrefixLens[i] = commonPrefixLen;
                commonPrefixLens[i - 1] = Math.max(commonPrefixLen, commonPrefixLens[i - 1]);
            }

            for (int i = 0; i < commonPrefixLens.length; i++) {
                int commonPrefixLen = commonPrefixLens[i];
                IndexedWord indexedWord = indexedWords.get(i);
                String abb = abbreviateWord(indexedWord.word, commonPrefixLen);
                ansArr[indexedWord.index] = abb;
            }
        }

        return Arrays.asList(ansArr);
    }

    private String abbreviateWord(String word, int index) {
        int len = word.length();
        if (len - index <= 3) {
            return word;
        }

        return word.substring(0, index + 1) + (len - index - 2) + word.charAt(len - 1);
    }

    private int longestCommonPrefix(String x, String y) {
        int index = 0;
        while (index < x.length() && index < y.length() && x.charAt(index) == y.charAt(index)) {
            index++;
        }
        return index;
    }

    static class IndexedWord {
        private String word;
        private int index;

        public IndexedWord(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }
}
