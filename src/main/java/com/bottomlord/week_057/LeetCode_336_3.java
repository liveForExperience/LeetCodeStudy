package com.bottomlord.week_057;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/8/7 8:16
 */
public class LeetCode_336_3 {
    public List<List<Integer>> palindromePairs(String[] words) {
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(new StringBuilder(words[i]).reverse().toString(), i);
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = words[i].length();

            int target = trie.find(word);
            if (target != -1 && target != i) {
                ans.add(Arrays.asList(i, target));
            }

            if (isPalindrome(word)) {
                int index = trie.find("");
                if (index != -1 && index != i) {
                    ans.add(Arrays.asList(i, index));
                }
            }

            for (int j = 0; j < len; j++) {
                String left = word.substring(0, j + 1),
                        right = word.substring(j + 1);

                if (isPalindrome(left)) {
                    int index = trie.find(right);
                    if (index != -1 && index != i) {
                        ans.add(Arrays.asList(index, i));
                    }
                }

                if (j != len - 1 && isPalindrome(right)) {
                    int index = trie.find(left);
                    if (index != -1 && index != i) {
                        ans.add(Arrays.asList(i, index));
                    }
                }
            }
        }
        return ans;
    }

    private boolean isPalindrome(String word) {
        int head = 0, tail = word.length() - 1;
        while (head < tail) {
            if (word.charAt(head) != word.charAt(tail)) {
                return false;
            }

            head++;
            tail--;
        }

        return true;
    }

    private class Trie {
        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word, int index) {
            char[] cs = word.toCharArray();
            TrieNode cur = root;

            for (char c : cs) {
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new TrieNode();
                }

                cur = cur.next[c - 'a'];
            }

            cur.target = index;
        }

        public int find(String word) {
            char[] cs = word.toCharArray();
            TrieNode cur = root;
            for (char c : cs) {
                TrieNode next = cur.next[c - 'a'];
                if (next == null) {
                    return -1;
                }

                cur = next;
            }

            return cur.target;
        }

        private class TrieNode {
            private TrieNode[] next = new TrieNode[26];
            private int target = -1;
        }
    }
}
