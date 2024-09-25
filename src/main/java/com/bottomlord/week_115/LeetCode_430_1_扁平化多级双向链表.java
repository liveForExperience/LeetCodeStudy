package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-24 08:35:39
 */
public class LeetCode_430_1_扁平化多级双向链表 {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Node start = new Node();
        dfs(start, head);
        start.next.prev = null;
        return start.next;
    }

    private Node dfs(Node pre, Node cur) {
        if (cur == null) {
            return pre;
        }

        Node next = cur.next;
        pre.next = cur;
        cur.prev = pre;

        Node child = dfs(cur, cur.child);

        cur.child = null;

        return dfs(child, next);
    }

    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
