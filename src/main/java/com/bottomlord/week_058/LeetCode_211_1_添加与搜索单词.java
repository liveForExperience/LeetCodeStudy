package com.bottomlord.week_058;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/8/13 8:36
 */
public class LeetCode_211_1_添加与搜索单词 {
    class WordDictionary {
        private TrieNode root;
        public WordDictionary() {
            this.root = new TrieNode();
        }

        public void addWord(String word) {
            if (word == null) {
                return;
            }

            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }

                node = node.children[index];
            }

            node.flag = true;
        }

        public boolean search(String word) {
            if (word == null) {
                return false;
            }

            return doSearch(word, root);
        }

        private boolean doSearch(String word, TrieNode node) {
            if (node == null) {
                return false;
            }

            if (Objects.equals("", word)) {
                return node.flag;
            }

            if (word.charAt(0) == '.') {
                for (int i = 0; i < 26; i++) {
                    boolean flag = doSearch(word.substring(1), node.children[i]);

                    if (flag) {
                        return true;
                    }
                }
                return false;
            }

            return doSearch(word.substring(1), node.children[word.charAt(0) - 'a']);
        }

        private class TrieNode {
            TrieNode[] children;
            boolean flag;

            TrieNode() {
                children = new TrieNode[26];
            }
        }
    }
}
