package com.bottomlord.week_119;

/**
 * @author chen yue
 * @date 2021-10-19 08:43:48
 */
public class LeetCode_211_1_添加与搜索单词_数据结构设计 {
    class WordDictionary {
        private DictNode root;
        public WordDictionary() {
            this.root = new DictNode();
        }

        public void addWord(String word) {
            char[] cs = word.toCharArray();
            DictNode node = root;
            for (char c : cs) {
                DictNode child = node.children[c - 'a'];
                if (child == null) {
                    child = new DictNode(c);
                    node.children[c - 'a'] = child;
                }
                node = child;
            }

            node.isLeaf = true;
        }

        public boolean search(String word) {
            char c = word.charAt(0);
            if (c == '.') {
                for (DictNode child : root.children) {
                    boolean result = doSearch(child, word, 1);
                    if (result) {
                        return true;
                    }
                }
                return false;
            }

            return doSearch(root.children[word.charAt(0) - 'a'], word, 1);
        }

        private boolean doSearch(DictNode node, String word, int index) {
            if (node == null) {
                return false;
            }

            if (index == word.length()) {
                   return node.isLeaf;
            }

            if (word.charAt(index) == '.') {
                DictNode[] children = node.children;
                for (DictNode child : children) {
                    boolean result = doSearch(child, word, index + 1);
                    if (result) {
                        return true;
                    }
                }

                return false;
            }

            return doSearch(node.children[word.charAt(index) - 'a'], word, index + 1);
        }
    }

    private static class DictNode {
        private char c;
        private boolean isLeaf;
        private DictNode[] children;

        public DictNode() {
            this.children = new DictNode[26];
        }

        public DictNode(char c) {
            this.c = c;
            this.children = new DictNode[26];
        }
    }
}
