package com.bottomlord.week_093;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/4/20 8:35
 */
public class LeetCode_527_1_单词缩写 {
    private TrieNode root = new TrieNode();

    public List<String> wordsAbbreviation(List<String> dict) {
        List<String> ans = new ArrayList<>();
        for (String word : dict) {
            addNode(root, word, 0, ans);
        }
        return ans;
    }

    static class TrieNode {
        private Map<String, Integer> map;
        private TrieNode[] nodes;

        TrieNode() {
            this.map = new HashMap<>();
            this.nodes = new TrieNode[26];
        }
    }

    private void addNode(TrieNode node, String word, int index, List<String> ans) {
        if (index == word.length()) {
            return;
        }

        if (node.nodes[word.charAt(index) - 'a'] == null) {
            node.nodes[word.charAt(index) - 'a'] = new TrieNode();
            String abbreviation = word.substring(0, index + 1) + (word.length() - index - 2 == 0 ? "" : (word.length() - index - 2));
            String suffix;
            if (index < word.length() - 1) {
                suffix = "" + word.charAt(word.length() - 1);
            } else if (index == word.length() - 1) {
                suffix = "" + word.charAt(word.length() - 1);
            } else {
                suffix = "";
            }
            abbreviation = abbreviation + suffix;
            ans.add(abbreviation.length() < word.length() ? abbreviation : word);
            return;
        }

        TrieNode trieNode = node.nodes[word.charAt(index) - 'a'];
        trieNode.map.put(word, index);

        addNode(trieNode, word, index + 1, ans);
    }
}
