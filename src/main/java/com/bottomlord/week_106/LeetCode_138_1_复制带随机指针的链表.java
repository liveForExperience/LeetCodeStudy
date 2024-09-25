package com.bottomlord.week_106;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/7/22 8:19
 */
public class LeetCode_138_1_复制带随机指针的链表 {
    public Node copyRandomList(Node head) {
        Node node = head;
        Node newHead = new Node(0);
        Node newNode = newHead;

        Map<Node, Node> mapping = new HashMap<>();

        while (node != null) {
            newNode.next = new Node(node.val);
            newNode = newNode.next;
            newNode.random = node.random;
            mapping.put(node, newNode);
            node = node.next;
        }

        node = newHead.next;
        while (node != null) {
            node.random = mapping.get(node.random);
            node = node.next;
        }

        return newHead.next;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
