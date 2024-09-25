package com.bottomlord.week_034;

/**
 * @author ThinkPad
 * @date 2020/2/27 8:11
 */
public class LeetCode_138_3 {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node node = head;
        while (node != null) {
            Node copy = new Node(node.val);
            copy.next = node.next;
            node.next = copy;
            node = copy.next;
        }

        node = head;
        while (node != null) {
            node.next.random = node.random != null ? node.random.next : null;
            node = node.next.next;
        }

        Node oldList = head, newList = head.next, newHead = head.next;
        while (oldList != null) {
            oldList.next = oldList.next.next;
            newList.next = newList.next != null ? newList.next.next : null;
            oldList = oldList.next;
            newList = newList.next;
        }

        return newHead;
    }
}
