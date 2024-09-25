package com.bottomlord.week_031;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/2/5 20:32
 */
public class LeetCode_146_2 {
    class LRUCache {
        private Map<Integer, DoubleLinkedNode> map;
        private int capacity;
        private int size;
        private DoubleLinkedNode head;
        private DoubleLinkedNode tail;

        public LRUCache(int capacity) {
            this.map = new HashMap<>();
            this.head = new DoubleLinkedNode();
            this.tail = new DoubleLinkedNode();
            head.next = tail;
            tail.pre = head;
            this.capacity = capacity;
            this.size = 0;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }

            DoubleLinkedNode node = map.get(key);
            moveToHead(node);
            return node.val;
        }

        public void put(int key, int value) {
            DoubleLinkedNode node = map.get(key);

            if (node != null) {
                node.val = value;
                moveToHead(node);
            } else {
                node = new DoubleLinkedNode(key, value);
                map.put(key, node);
                addNode(node);

                size++;
                if (size > capacity) {
                    DoubleLinkedNode tail = popTail();
                    map.remove(tail.key);
                    size--;
                }
            }
        }

        private void addNode(DoubleLinkedNode node) {
            node.pre = head;
            node.next = head.next;

            head.next.pre = node;
            head.next = node;
        }

        private void removeNode(DoubleLinkedNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private void moveToHead(DoubleLinkedNode node) {
            removeNode(node);
            addNode(node);
        }

        private DoubleLinkedNode popTail() {
            DoubleLinkedNode tail = this.tail.pre;
            removeNode(tail);
            return tail;
        }

        private class DoubleLinkedNode {
            public int key;
            public int val;
            public DoubleLinkedNode pre;
            public DoubleLinkedNode next;

            public DoubleLinkedNode() {}
            public DoubleLinkedNode(int key, int val){
                this.key = key;
                this.val = val;
            }
        }
    }
}
