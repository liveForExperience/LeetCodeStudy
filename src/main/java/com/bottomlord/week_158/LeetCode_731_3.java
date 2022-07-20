package com.bottomlord.week_158;

/**
 * @author chen yue
 * @date 2022-07-20 22:40:33
 */
public class LeetCode_731_3 {
    class MyCalendarTwo {
        private SegmentTree segmentTree;
        private Node root;

        public MyCalendarTwo() {
            segmentTree = new SegmentTree();
            root = new Node();
        }

        public boolean book(int start, int end) {
            if (segmentTree.query(root, 0, (int)10e9, start, end - 1) == 2) {
                return false;
            }
            segmentTree.update(root, 0, (int)1e9, start, end - 1, 1);
            return true;
        }

        class SegmentTree {

            public void update(Node node, int start, int end, int l, int r, int val) {
                if (start >= l && end <= r) {
                    node.val += val;
                    node.add += val;
                    return;
                }

                int mid = (start + end) >> 1;
                pushDown(node);

                if (l <= mid) {
                    update(node.left, start, mid, l, r, val);
                }

                if (r > mid) {
                    update(node.right, mid + 1, end, l, r, val);
                }

                pushUp(node);
            }

            private void pushDown(Node node) {
                if (node.left == null) {
                    node.left = new Node();
                }

                if (node.right == null) {
                    node.right = new Node();
                }

                if (node.add == 0) {
                    return;
                }

                node.left.val += node.add;
                node.right.val += node.add;
                node.left.add += node.add;
                node.right.add += node.add;
                node.add = 0;
            }

            public int query(Node node, int start, int end, int l, int r) {
                if (l <= start && r >= end) {
                    return node.val;
                }

                pushDown(node);
                int mid = (start + end) >> 1, ans = 0;
                if (l <= mid) {
                    ans = query(node.left, start, mid, l, r);
                }

                if (r > mid) {
                    ans = Math.max(ans, query(node.right, mid + 1, end, l, r));
                }

                return ans;
            }

            private void pushUp(Node node) {
                node.val = Math.max(node.left.val, node.right.val);
            }
        }

        class Node {
            private Node left, right;
            private int val, add;
        }
    }
}
