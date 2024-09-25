package com.bottomlord.week_154;

/**
 * @author chen yue
 * @date 2022-06-20 22:15:33
 */
public class LeetCode_715_1_Range模块 {
    class RangeModule {

        public RangeModule() {

        }

        public void addRange(int left, int right) {
            update(root, 1, N, left, right - 1, 1);
        }

        public boolean queryRange(int left, int right) {
            return query(root, 1, N, left, right - 1);
        }

        public void removeRange(int left, int right) {
            update(root, 1, N, left, right - 1, -1);
        }

        private int N = (int)1e9;
        private Node root = new Node();

        public void update(Node node, int start, int end, int l, int r, int val) {
            if (l <= start && r >= end) {
                node.cover = val == 1;
                node.add = val;
                return;
            }

            int mid = (start + end) >> 1;
            pushDown(node);

            if (l <= mid) {
                update(node, start, mid, l, r, val);
            }

            if (r > mid) {
                update(node, mid + 1, end, l, r, val);
            }

            pushUp(node);
        }

        public boolean query(Node node, int start, int end, int l, int r) {
            if (l <= start && r >= end) {
                return node.cover;
            }

            boolean ans = true;
            int mid = (start + end) >> 1;

            pushDown(node);

            if (l <= mid) {
                ans = query(node, start, mid, l, r);
            }

            if (r > mid) {
                ans = query(node, mid + 1, end, l, r);
            }

            return ans;
        }

        public void pushDown(Node node) {
            if (node.left == null) {
                node.left = new Node();
            }

            if (node.right == null) {
                node.right = new Node();
            }

            if (node.add == 0) {
                return;
            }

            node.left.cover = node.add == 1;
            node.right.cover = node.add == 1;
            node.left.add = node.add;
            node.right.add = node.add;
            node.add = 0;
        }

        public void pushUp(Node node) {
            node.cover = node.left.cover && node.right.cover;
        }
    }

    class Node {
        private Node left, right;
        private boolean cover;
        private int add;
    }
}
