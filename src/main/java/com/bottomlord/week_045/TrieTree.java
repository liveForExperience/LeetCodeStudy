package com.bottomlord.week_045;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/5/12 8:19
 */
public class TrieTree {
    class TrieNode {
        private TrieNode[] children;
        private boolean flag;
        private int index;

        public TrieNode() {
            children = new TrieNode[26];
            flag = false;
            index = -1;
        }
    }

    List<Integer>[] ans;
    TrieNode root;

    public TrieTree(String[] smalls) {
        root = new TrieNode();
        int len = smalls.length;
        ans = new List[len];
        for (int i = 0; i < len; i++) {
            ans[i] = new ArrayList<>();
        }
    }

    public void insert(String word, int pos) {
        int len = word.length();
        char[] cs = word.toCharArray();

        TrieNode pivot = root;
        for (int i = 0; i < cs.length; i++) {
            int index = cs[i] - 'a';

            if (pivot.children[index] == null) {
                pivot.children[index] = new TrieNode();
            }

            pivot = pivot.children[index];
        }

        pivot.flag = true;
        pivot.index = pos;
    }

    public void update(String word, int start) {
        int len = word.length();
        char[] cs = word.toCharArray();

        TrieNode pivot = root;
        for (int i = 0; i < len; i++) {
            int index = cs[i] - 'a';

            TrieNode node = pivot.children[index];
            if (node == null) {
                return;
            }

            if (node.flag) {
                ans[node.index].add(start);
            }

            pivot = node;
        }
    }
}
