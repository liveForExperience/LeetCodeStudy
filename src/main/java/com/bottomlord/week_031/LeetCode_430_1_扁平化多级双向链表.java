package com.bottomlord.week_031;

/**
 * @author ThinkPad
 * @date 2020/2/4 15:24
 */
public class LeetCode_430_1_扁平化多级双向链表 {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Node start = dfs(new Node(), head);
        start.next.prev = null;
        return start.next;
    }

    private Node dfs(Node pre, Node cur) {
        if (cur == null) {
            return pre;
        }

        pre.next = cur;
        cur.prev = pre;

        Node next = cur.next;
        Node child = dfs(cur, cur.child);

        cur.child = null;

        return dfs(child, next);
    }
}