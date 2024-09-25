package com.bottomlord.week_153;


/**
 * @author chen yue
 * @date 2022-06-18 10:32:50
 */
public class LeetCode_offerII029_1_排序的环形链表 {
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (head == null) {
            return node;
        }

        Node cur = head, next = cur.next;
        if (next == null) {
            cur.next = node;
            node.next = cur;
            return head;
        }

        while (next != head) {
            int curVal = cur.val, nextVal = next.val;

            if (curVal > nextVal) {
                if (insertVal > curVal) {
                    cur.next = node;
                    node.next = next;
                    return head;
                } else if (insertVal < curVal && insertVal < nextVal) {
                    cur.next = node;
                    node.next = next;
                    return head;
                }
            } else if (insertVal > curVal && insertVal < nextVal){
                cur.next = node;
                node.next = next;
                return head;
            }

            cur = next;
            next = cur.next;
        }

        cur.next = node;
        node.next = next;

        return head;
    }

    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };
}
