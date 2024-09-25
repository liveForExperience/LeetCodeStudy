package com.bottomlord.week_157;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-07-14 08:59:02
 */
public class LeetCode_745_1_前缀和后缀搜索 {
    class WordFilter {
        private TrieTree p, n;
        public WordFilter(String[] words) {
            p = new TrieTree();
            n = new TrieTree();

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                p.insert(word, i);
                n.insert(reserve(word), i);
            }
        }

        public int f(String pref, String suff) {
            List<Integer> pis = p.query(pref),
                          nis = n.query(suff);

            Set<Integer> set = new HashSet<>(pis);
            int max = -1;
            for (int num : nis) {
                if (set.contains(num)) {
                    max = Math.max(max, num);
                }
            }

            return max;
        }

        private class TrieTree {
            private TrieNode root;

            public TrieTree() {
                this.root = new TrieNode();
            }

            public void insert(String str, int index) {
                TrieNode node = root;
                char[] cs = str.toCharArray();

                for (char c : cs) {
                    TrieNode child = node.children[c - 'a'];
                    if (child == null) {
                        child = new TrieNode();
                    }

                    child.idxes.add(index);
                    node = child;
                }
            }

            public List<Integer> query(String str) {
                TrieNode node = root;
                char[] cs = str.toCharArray();

                for (char c : cs) {
                    TrieNode child = node.children[c - 'a'];
                    if (child == null) {
                        return new ArrayList<>();
                    }

                    node = child;
                }

                return node.idxes;
            }
        }

        private class TrieNode {
            private List<Integer> idxes;
            private TrieNode[] children;

            public TrieNode() {
                this.idxes = new ArrayList<>();
                this.children = new TrieNode[26];
            }
        }

        private String reserve(String str) {
            char[] cs = str.toCharArray();
            int l = 0, r = cs.length - 1;
            while (l < r) {
                char c = cs[l];
                cs[l] = cs[r];
                cs[r] = c;

                l++;
                r--;
            }

            return new String(cs);
        }
    }
}
