package com.bottomlord.week_058;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/8/16 10:58
 */
public class LeetCode_212_1_单词搜索II {
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        int row = board.length;
        if (row == 0) {
            return Collections.emptyList();
        }

        int col = board[0].length;
        if (col == 0) {
            return Collections.emptyList();
        }

        Set<String> ansSet = new HashSet<>();

        Trie trie = new Trie();
        for (String word : words) {
            if (Objects.equals(word, "")) {
                ansSet.add(word);
            }
            trie.insert(word);
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                backTrack(trie, board, row, col, r, c, new StringBuilder(), ansSet);
            }
        }

        List<String> ans = new ArrayList<>(ansSet);
        Collections.sort(ans);
        return ans;
    }

    private void backTrack(Trie trie, char[][] board, int row, int col, int r, int c, StringBuilder sb, Set<String> ansSet) {
        if (!ansSet.contains(sb.toString()) && trie.isWord(sb.toString())) {
            ansSet.add(sb.toString());
        }

        if (r < 0 || r >= row || c < 0 || c >= col || board[r][c] == '#') {
            return;
        }

        if (sb.length() != 0 && !trie.search(sb.toString())) {
            return;
        }

        for (int[] direction : directions) {
            char cr = board[r][c];
            sb.append(cr);
            board[r][c] = '#';
            backTrack(trie, board, row, col, r + direction[0], c + direction[1], sb, ansSet);
            sb.deleteCharAt(sb.length() - 1);
            board[r][c] = cr;
        }
    }

    private class Trie {
        private TrieNode root;

        private Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
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
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';

                if (node.children[index] == null) {
                    return false;
                }

                node = node.children[index];
            }

            return true;
        }

        public boolean isWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';

                if (node.children[index] == null) {
                    return false;
                }

                node = node.children[index];
            }

            return node.flag;
        }

        private class TrieNode {
            private TrieNode[] children;
            private boolean flag;

            public TrieNode() {
                this.children = new TrieNode[26];
            }
        }
    }
}
