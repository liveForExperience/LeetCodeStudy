package com.bottomlord.week_157;

/**
 * @author chen yue
 * @date 2022-06-20 21:02:06
 */
public class LeetCode_732_1_我的日程安排III {
    class MyCalendarThree {

        public MyCalendarThree() {
            this.root = new Node();
        }

        public int book(int start, int end) {
            update(root, 0, (int)1e9, start, end - 1, 1);
            return root.val;
        }

        private Node root;

        private void update(Node node, int start, int end, int l, int r, int val) {
            if (l <= start && r >= end) {
                node.val += val;
                node.add += val;
                return;
            }

            pushDown(node);
            int mid = (start + end) >> 1;

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

        private void pushUp(Node node) {
            node.val = Math.max(node.left.val, node.right.val);
        }

        private class Node {
            private Node left, right;
            private int val, add;
        }
    }
}
