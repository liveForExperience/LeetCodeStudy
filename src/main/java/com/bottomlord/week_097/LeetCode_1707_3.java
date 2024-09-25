package com.bottomlord.week_097;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ChenYue
 * @date 2021/5/23 15:49
 */
public class LeetCode_1707_3 {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        int len = queries.length;
        int[][] nQueries = new int[len][3];
        for (int i = 0; i < queries.length; i++) {
            nQueries[i][0] = queries[i][0];
            nQueries[i][1] = queries[i][1];
            nQueries[i][2] = i;
        }

        Arrays.sort(nQueries, Comparator.comparingInt(x -> x[1]));

        int[] ans = new int[len];
        TrieTree trieTree = new TrieTree();
        int ni = 0;
        for (int[] query : nQueries) {
            int x = query[0], m = query[1], qi = query[2];
            while (ni < nums.length && nums[ni] <= m) {
                trieTree.add(nums[ni++]);
            }

            if (ni == 0) {
                ans[qi] = -1;
            } else {
                ans[qi] = trieTree.getMaxXor(x) ^ x;
            }
        }

        return ans;
    }

    private class TrieTree {
        private TrieNode root;

        public TrieTree() {
            root = new TrieNode();
        }

        public void add(int num) {
            doAdd(root, num, 30);
        }

        private void doAdd(TrieNode node, int num, int index) {
            if (index < 0) {
                node.val = num;
                return;
            }

            int mask = 1;
            if ((num & (mask << index)) == 0) {
                if (node.zero == null) {
                    node.zero = new TrieNode();
                }
                doAdd(node.zero, num, index - 1);
            } else {
                if (node.one == null) {
                    node.one = new TrieNode();
                }
                doAdd(node.one, num, index - 1);
            }
        }

        public int getMaxXor(int query) {
            return doGetMaxXor(root, query, 30);
        }

        private int doGetMaxXor(TrieNode node, int num, int index) {
            if (node.val != null) {
                return node.val;
            }

            int mask = 1;
            if ((num & (mask << index)) == 0) {
                if (node.one != null) {
                    return doGetMaxXor(node.one, num, index - 1);
                } else {
                    return doGetMaxXor(node.zero, num, index - 1);
                }
            } else {
                if (node.zero != null) {
                    return doGetMaxXor(node.zero, num, index - 1);
                } else {
                    return doGetMaxXor(node.one, num, index - 1);
                }
            }
        }

        private class TrieNode {
            private Integer val;
            private TrieNode zero;
            private TrieNode one;
        }
    }
}