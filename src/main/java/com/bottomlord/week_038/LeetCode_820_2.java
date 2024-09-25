package com.bottomlord.week_038;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/3/28 16:48
 */
public class LeetCode_820_2 {
    public int minimumLengthEncoding(String[] words) {
        TrieNode root = new TrieNode();
        Map<TrieNode, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            TrieNode cur = root;
            for (int j = words[i].length() - 1; j >= 0; j--) {
                cur = cur.get(words[i].charAt(j));
            }
            map.put(cur, i);
        }

        int ans = 0;
        for (TrieNode node : map.keySet()) {
            if (node.count == 0) {
                ans += words[map.get(node)].length() + 1;
            }
        }

        return ans;
    }


    private class TrieNode {
        private TrieNode[] children;
        private int count;

        public TrieNode() {
            children = new TrieNode[26];
            count = 0;
        }

        public TrieNode get(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new TrieNode();
                count++;
            }

            return children[c - 'a'];
        }
    }
}
