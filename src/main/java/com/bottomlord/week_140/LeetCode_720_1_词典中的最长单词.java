package com.bottomlord.week_140;

/**
 * @author chen yue
 * @date 2022-03-17 11:34:48
 */
public class LeetCode_720_1_词典中的最长单词 {
    public String longestWord(String[] words) {
        TrieTree tree = new TrieTree();
        for (String word : words) {
            tree.insert(word);
        }

        String ans = "";
        for (String word : words) {
            if (tree.search(word)) {
                if (word.length() > ans.length() || (word.length() == ans.length() && word.compareTo(ans) < 0)) {
                    ans = word;
                }
            }
        }

        return ans;
    }

    private static class TrieTree {
        private TrieNode root;
        public TrieTree() {
            this.root = new TrieNode(' ');
        }

        public void insert(String word) {
            char[] cs = word.toCharArray();
            TrieNode node = root;
            for (char c : cs) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode(c);
                }

                node = node.children[c - 'a'];
            }

            node.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode node = root;
            char[] cs = word.toCharArray();

            for (char c : cs) {
                if (node.children[c - 'a'] == null || !node.children[c - 'a'].isEnd) {
                    return false;
                }

                node = node.children[c - 'a'];
            }

            return node.isEnd;
        }
    }

    private static class TrieNode {
        private char c;
        private boolean isEnd;
        private TrieNode[] children;

        public TrieNode(char c) {
            this.c = c;
            this.children = new TrieNode[26];
        }
    }
}
