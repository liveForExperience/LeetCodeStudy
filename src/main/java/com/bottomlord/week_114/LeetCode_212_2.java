package com.bottomlord.week_114;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-09-16 08:50:27
 */
public class LeetCode_212_2 {
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        int row = board.length, col = board[0].length;

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> set = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                backTrack(i, j, row, col, trie, board, new StringBuilder(), set);
            }
        }

        return new ArrayList<>(set);
    }

    private void backTrack(int x, int y, int row, int col, Trie trie, char[][] board, StringBuilder sb, Set<String> set) {
        if (x < 0 || x >= row || y < 0 || y >= col || board[x][y] == '#') {
            return;
        }

        char c = board[x][y];
        int len = sb.length();
        sb.append(c);
        String str = sb.toString();
        board[x][y] = '#';

        if (!trie.search(str)) {
            sb.setLength(len);
            board[x][y] = c;
            return;
        }

        if (trie.isWord(str)) {
            set.add(str);
        }

        for (int[] direction : directions) {
            backTrack(x + direction[0], y + direction[1], row, col, trie, board, sb, set);
        }

        sb.setLength(len);
        board[x][y] = c;
    }

    private class Trie {
        private final TireNode root;

        public Trie() {
            this.root = new TireNode();
        }

        public void insert(String word) {
            TireNode node = root;

            char[] cs = word.toCharArray();
            for (char c : cs) {
                int index = c - 'a';

                if (node.children[index] == null) {
                    node.children[index] = new TireNode();
                }

                node = node.children[index];
            }

            node.isWord = true;
        }

        public boolean search(String word) {
            return doSearch(word) != null;
        }

        public boolean isWord(String word) {
            TireNode node = doSearch(word);
            return node != null && node.isWord;
        }

        private TireNode doSearch(String word) {
            TireNode node = root;

            char[] cs = word.toCharArray();
            for (char c : cs) {
                int index = c - 'a';

                TireNode childNode = node.children[index];
                if (childNode == null) {
                    return null;
                }

                node = childNode;
            }

            return node;
        }

        private class TireNode {
            private final TireNode[] children;
            private boolean isWord;

            public TireNode() {
                this.children = new TireNode[26];
            }
        }
    }
}
