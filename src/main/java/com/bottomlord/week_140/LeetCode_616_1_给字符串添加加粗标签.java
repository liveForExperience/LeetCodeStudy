package com.bottomlord.week_140;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2022-03-18 22:55:46
 */
public class LeetCode_616_1_给字符串添加加粗标签 {
    public String addBoldTag(String s, String[] words) {
        int len = s.length();
        boolean[] bucket = new boolean[len];
        char[] cs = s.toCharArray();
        TrieTree tree = new TrieTree();
        tree.insert(s);

        for (String word : words) {
            List<TrieNode> nodes = tree.listNode(word.charAt(0));
            for (TrieNode node : nodes) {
                if (tree.isWord(node, word)) {
                    for (int i = node.depth; i < node.depth + word.length(); i++) {
                        bucket[i] = true;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bucket.length;) {
            int count = 0;
            StringBuilder curSb = new StringBuilder();
            while (i < bucket.length && bucket[i]) {
                count++;
                curSb.append(cs[i++]);
            }

            if (count == 0) {
                sb.append(cs[i]);
                i++;
            } else {
                sb.append("<b>").append(curSb).append("</b>");
            }
        }

        return sb.toString();
    }

    private class TrieTree {
        private TrieNode root;
        public TrieTree() {
            this.root = new TrieNode(' ', -1);
        }

        public void insert(String s) {
            TrieNode node = root;
            char[] cs = s.toCharArray();
            for (int i = 0; i < cs.length; i++) {
                node = node.addChild(cs[i], i);
            }
        }

        public List<TrieNode> listNode(char c) {
            List<TrieNode> ans = new ArrayList<>();
            Queue<TrieNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TrieNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                if (node.c == c) {
                    ans.add(node);
                }

                for (TrieNode child : node.children) {
                    if (child == null) {
                        continue;
                    }

                    queue.offer(child);
                }
            }

            return ans;
        }

        public boolean isWord(TrieNode node, String word) {
            if (word == null || word.length() == 0) {
                return true;
            }

            if (node.c != word.charAt(0)) {
                return false;
            }

            char[] cs = word.toCharArray();
            for (int i = 1; i < cs.length; i++) {
                char c = cs[i];
                if (node.children[c] == null) {
                    return false;
                }

                node = node.children[c];
            }

            return true;
        }
    }

    private class TrieNode {
        private char c;
        private int depth;
        private TrieNode[] children;

        public TrieNode(char c, int depth) {
            this.c = c;
            this.depth = depth;
            this.children = new TrieNode[123];
        }

        public TrieNode addChild(char c, int depth) {
            if (this.children[c] != null) {
                return this.children[c];
            }

            this.children[c] = new TrieNode(c, depth);
            return this.children[c];
        }
    }
}
