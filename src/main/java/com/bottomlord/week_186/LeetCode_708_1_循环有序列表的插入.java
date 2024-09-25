package com.bottomlord.week_186;


/**
 * @author chen yue
 * @date 2023-02-05 15:30:16
 */
public class LeetCode_708_1_循环有序列表的插入 {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }

        Node pre = head, node = head.next;
        if (node == pre) {
            pre.next = new Node(insertVal);
            pre.next.next = pre;
            return pre;
        }

        while (node != null) {
            if ((insertVal >= pre.val && insertVal <= node.val) ||
                    (insertVal <= pre.val && insertVal <= node.val && node.val < pre.val) ||
                    (insertVal >= pre.val && insertVal >= node.val && node.val < pre.val) ||
                    node == head) {
                pre.next = new Node(insertVal);
                pre.next.next = node;
                return head;
            }

            pre = node;
            node = node.next;
        }

        return null;
    }

    static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    ;
}
