package com.bottomlord.week_092;

import com.bottomlord.TreeNode;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2021/4/14 8:26
 */
public class LeetCode_208_1_实现Trie前缀树 {
    class Trie {
        private TrieNode root;
        /** Initialize your data structure here. */
        public Trie() {
            this.root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            doInsert(word, 0, root);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return doSearch(word, 0, root, true);
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return doSearch(prefix, 0, root, false);
        }

        private void doInsert(String word, int index, TrieNode node) {
            if (index == word.length()) {
                return;
            }

            int ci = word.charAt(index) - 'a';
            if (node.nodes[ci] == null) {
                node.nodes[ci] = new TrieNode();
            }

            if (index == word.length() - 1) {
                node.nodes[ci].isWord = true;
            }

            doInsert(word, index + 1, node.nodes[ci]);
        }

        private boolean doSearch(String str, int index, TrieNode node, boolean needWord) {
            if (index == str.length()) {
                if (needWord) {
                    return node.isWord;
                }

                return true;
            }

            TrieNode curNode = node.nodes[str.charAt(index) - 'a'];
            if (curNode == null) {
                return false;
            }

            return doSearch(str, index + 1, curNode, needWord);
        }
    }

    static class TrieNode {
        private TrieNode[] nodes;
        private boolean isWord;

        public TrieNode() {
            this.nodes = new TrieNode[26];
            this.isWord = false;
        }
    }
}
