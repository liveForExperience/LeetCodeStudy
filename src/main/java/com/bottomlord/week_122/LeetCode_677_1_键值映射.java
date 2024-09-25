package com.bottomlord.week_122;

import java.util.*;

/**
 * @author chen yue
 * @date 2021-11-14 21:09:14
 */
public class LeetCode_677_1_键值映射 {
    static class MapSum {
        private DictTree tree;
        public MapSum() {
            this.tree = new DictTree();
        }

        public void insert(String key, int val) {
            this.tree.insert(key, val);
        }

        public int sum(String prefix) {
            return tree.search(prefix);
        }

        private  class DictTree {
            private Map<String, Integer> mapping;
            private DictNode root;

            public DictTree() {
                this.mapping = new HashMap<>();
                this.root = new DictNode(' ');
            }

            public void insert(String str, Integer val) {
                this.mapping.put(str, val);
                char[] cs = str.toCharArray();
                DictNode node = root;
                for (char c : cs) {
                    if (node.children[c - 'a'] == null) {
                        node.children[c - 'a'] = new DictNode(c);
                    }

                    node = node.children[c - 'a'];
                }
            }

            public Integer search(String str) {
                char[] cs = str.toCharArray();
                DictNode node = root;
                for (char c : cs) {
                    node = node.children[c - 'a'];
                    if (node == null) {
                        return 0;
                    }
                }
                return dfs(node, new StringBuilder(str));
            }

            private int dfs(DictNode node, StringBuilder sb) {
                if (node == null) {
                    return 0;
                }

                if (node.isEmpty()) {
                    return mapping.getOrDefault(sb.toString(), 0);
                }

                int sum = mapping.getOrDefault(sb.toString(), 0);
                DictNode[] children = node.children;
                for (int i = 0; i < 26; i++) {
                    DictNode child = children[i];
                    int len = sb.length();
                    sum += dfs(child, sb.append((char)('a' + i)));
                    sb.setLength(len);
                }

                return sum;
            }
        }

        private  class DictNode {
            private char c;
            private DictNode[] children;

            public DictNode(char c) {
                this.c = c;
                this.children = new DictNode[26];
            }

            public boolean isEmpty() {
                for (DictNode child : children) {
                    if (child != null) {
                        return false;
                    }
                }
                return true;
            }
        }
    }
}
