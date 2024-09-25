package com.bottomlord.week_193;

/**
 * @author chen yue
 * @date 2023-03-24 18:01:01
 */
public class LeetCode_1032_1_字符流 {
    class StreamChecker {
        private StringBuilder sb;
        private Trie root;
        public StreamChecker(String[] words) {
            sb = new StringBuilder();
            root = new Trie();
            for (String word : words) {
                root.insert(word);
            }
        }

        public boolean query(char letter) {
            sb.append(letter);
            return root.query(sb);
        }
    }

    private static class Trie {
        private boolean isEnd;
        private final Trie[] children = new Trie[26];

        public void insert(String word) {
            Trie node = this;
            char[] cs = word.toCharArray();
            for (int i = cs.length - 1; i >= 0; i--) {
                char c = cs[i];

                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new Trie();
                }

                node = node.children[c - 'a'];
            }

            node.isEnd = true;
        }

        public boolean query(StringBuilder sb) {
            int len = sb.length();
            Trie node = this;
            for (int i = len - 1; i >= 0; i--) {
                char c = sb.charAt(i);

                if (node.children[c - 'a'] == null) {
                    return false;
                }

                node = node.children[c - 'a'];
                if (node.isEnd) {
                    return true;
                }
            }

            return false;
        }
    }
}
