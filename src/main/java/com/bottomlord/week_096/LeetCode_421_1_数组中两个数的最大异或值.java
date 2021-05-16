package com.bottomlord.week_096;

public class LeetCode_421_1_数组中两个数的最大异或值 {

    public int findMaximumXOR(int[] nums) {
        TrieTree tree = new TrieTree();
        for (int num : nums) {
            tree.add(num);
        }

        int ans = Integer.MIN_VALUE;
        for (int num : nums) {
            ans = Math.max(ans, num ^ tree.find(num));
        }
        return ans;
    }

    class TrieTree {
        private TrieNode root;

        public TrieTree() {
            this.root = new TrieNode();
        }

        public void add(int num) {
            doAdd(num, 31, root);
        }

        public int find(int num) {
            return doFind(num, 31, root);
        }

        private void doAdd(int num, int index, TrieNode node) {
            if (index < 0) {
                node.val = num;
                return;
            }

            int bit = 1 << index;
            if ((bit & num) == 0) {
                if (node.zero == null) {
                    node.zero = new TrieNode();
                }
                doAdd(num, index - 1, node.zero);
            } else {
                if (node.one == null) {
                    node.one = new TrieNode();
                }
                doAdd(num, index - 1, node.one);
            }
        }

        private int doFind(int num, int index, TrieNode node) {
            if (node.val != null) {
                return node.val;
            }

            int bit = 1 << index;
            if ((bit & num) == 0) {
                if (node.one != null) {
                    return doFind(num, index - 1, node.one);
                } else {
                    return doFind(num, index - 1, node.zero);
                }
            } else {
                if (node.zero != null) {
                    return doFind(num, index - 1, node.zero);
                } else {
                    return doFind(num, index - 1, node.one);
                }
            }
        }

        class TrieNode {
            private Integer val;
            private TrieNode zero;
            private TrieNode one;
        }
    }
}
