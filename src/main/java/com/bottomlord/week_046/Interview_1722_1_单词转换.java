package com.bottomlord.week_046;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/5/20 8:58
 */
public class Interview_1722_1_单词转换 {
    private boolean[] memo;
    private List<String> wordList;
    private List<String> result;
    private String endWord;
    private List<String> path;

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        this.wordList=wordList;
        this.result=new ArrayList();
        this.wordList = wordList;
        this.path = new ArrayList<>();
        this.memo = new boolean[wordList.size()];
        this.endWord=endWord;
        backTrack(beginWord);
        return result;
    }

    private void backTrack(String beginWord) {
        this.path.add(beginWord);
        List<String> list = findWords(beginWord);
        for (String word : list) {
            if (Objects.equals(word, endWord)) {
                path.add(word);
                result = new ArrayList<>(path);
                return;
            }

            backTrack(word);

            path.remove(path.size() - 1);
        }
    }

    private List<String> findWords(String str) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);
            int diff = 0;
            if (memo[i] || word.length() != str.length()) {
                continue;
            }

            for (int j = 0; j < word.length(); j++) {
                if (diff > 1) {
                    break;
                }

                if (word.charAt(j) != str.charAt(j)) {
                    diff++;
                }
            }

            if (diff == 1) {
                list.add(word);
                memo[i] = true;
            }
        }

        return list;
    }
}
