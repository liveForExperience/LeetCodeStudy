package com.bottomlord.week_020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_648_1_单词替换 {
    private class TrieNode {
        private String val;
        private Map<Character, TrieNode> next;

        public TrieNode() {
            next = new HashMap<>();
        }
    }

    private TrieNode root = new TrieNode();

    private void insert(String str) {
        TrieNode node = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!node.next.containsKey(c)) {
                node.next.put(c, new TrieNode());
            }
            node = node.next.get(c);
        }
        node.val = str;
    }

    private String get(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (node.val != null) {
                return node.val;
            }

            if (node.next.containsKey(c)) {
                node = node.next.get(c);
            } else {
                return word;
            }
        }

        return word;
    }

    public String replaceWords(List<String> dict, String sentence) {
        for (String str : dict) {
            insert(str);
        }

        int i = 0, j = 0, len = sentence.length();
        StringBuilder sb = new StringBuilder();
        while (j < len) {
            while (j < len && sentence.charAt(j) != ' ') {
                j++;
            }

            sb.append(get(sentence.substring(i, j))).append(" ");

            i = j + 1;
        }

        return sb.toString().substring(0, sb.length() - 1);
    }
}
