package com.bottomlord.week_129;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-12-28 08:52:02
 */
public class LeetCode_472_1_连接词 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Tire tire = new Tire();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (dfs(tire.root, 0, word)) {
                ans.add(word);
            } else {
                tire.insert(word);
            }
        }
        return ans;
    }

    private boolean dfs(TireNode root, int index, String word) {
        if (index == word.length()) {
            return true;
        }

        TireNode node = root;
        while (index < word.length()) {
            node = node.children[word.charAt(index) - 'a'];
            if (node == null) {
                return false;
            }

            if (node.isWord && dfs(root, index + 1, word)) {
                return true;
            }

            index++;
        }

        return false;
    }

    private class Tire {
        private TireNode root;

        public Tire() {
            this.root = new TireNode();
        }

        public void insert(String word) {
            TireNode node = root;
            char[] cs = word.toCharArray();
            for (char c : cs) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TireNode();
                }

                node = node.children[c - 'a'];
            }

            node.isWord = true;
        }
    }

    private class TireNode {
        private char c;
        private boolean isWord;
        private TireNode[] children;

        public TireNode() {
            this.children = new TireNode[26];
        }
    }
}
