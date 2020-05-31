package com.bottomlord.week_047;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/5/31 17:47
 */
public class Interview_1725_1_单词矩阵 {
    private int maxArea = 0;
    private List<String> ans = new ArrayList<>();
    public String[] maxRectangle(String[] words) {
        int maxLen = 0;
        Trie root = new Trie();
        for (String word : words) {
            Trie node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new Trie();
                }

                node = node.children[c - 'a'];
            }

            node.isLeaf = true;
        }

        Map<Integer, Set<String>> map = new HashMap<>();
        for (String word : words) {
            Integer len = word.length();
            Set<String> set = map.getOrDefault(len, new HashSet<>());
            maxLen = Math.max(maxLen, len);
            set.add(word);
            map.put(len, set);
        }

        List<String> path = new ArrayList<>();
        for (Integer len : map.keySet()) {
            path.clear();
            backTrack(root, map.get(len), path, len, maxLen);
        }

        return ans.toArray(new String[0]);
    }

    private void backTrack(Trie root, Set<String> words, List<String> path, int len, int maxLen) {
        if (len * maxLen <= maxArea) {
            return;
        }

        if (path.size() > maxLen) {
            return;
        }

        for (String word : words) {
            path.add(word);

            boolean[] res = isValid(root, path);
            if (res[0]) {
                int area = path.size() * path.get(0).length();
                if (area > maxArea && res[1]) {
                    maxArea = area;
                    ans = new ArrayList<>(path);
                }

                backTrack(root, words, path, len, maxLen);
            }

            path.remove(path.size() - 1);
        }
    }

    private boolean[] isValid(Trie root, List<String> path) {
        boolean allLeaf = true;
        int row = path.size(), col = path.get(0).length();
        for (int i = 0; i < col; i++) {
            Trie node = root;
            for (int j = 0; j < row; j++) {
                int index = path.get(j).charAt(i) - 'a';

                if (node.children[index] == null) {
                    return new boolean[]{false, false};
                }
                node = node.children[index];
            }

            if (!node.isLeaf) {
                allLeaf = false;
            }
        }

        return new boolean[]{true, allLeaf};
    }

    class Trie {
        private Trie[] children;
        private boolean isLeaf;
        public Trie() {
            children = new Trie[26];
        }
    }
}