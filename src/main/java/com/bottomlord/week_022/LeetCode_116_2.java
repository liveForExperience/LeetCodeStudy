package com.bottomlord.week_022;

public class LeetCode_116_2 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node pre = root, start = pre, cur = null;

        while (pre.left != null) {
            if (cur == null) {
                pre.left.next = pre.right;
                pre = start.left;
                cur = start.right;
                start = pre;
            } else {
                pre.left.next = pre.right;
                pre.right.next = cur.left;
                pre = pre.next;
                cur = cur.next;
            }
        }

        return root;
    }
}