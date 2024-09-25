package com.bottomlord.week_218;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-09-24 20:44:05
 */
public class LeetCode_146_1_LRU缓存 {
    class LRUCache {
        private Map<Integer, DListNode> cache;
        private int capacity, size;
        private DListNode head, tail;

        public LRUCache(int capacity) {
            this.cache = new HashMap<>();
            this.size = 0;
            this.capacity = capacity;
            head = new DListNode();
            tail = new DListNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DListNode node = cache.get(key);
            if (node == null) {
                return -1;
            }

            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DListNode node = cache.get(key);
            if (node == null) {
                node = new DListNode(key, value);
                cache.put(key, node);
                addToHead(node);
                size++;

                if (size > capacity) {
                    DListNode tail = removeTail();
                    cache.remove(tail.key);
                    size--;
                }

                return;
            }

            node.value = value;
            moveToHead(node);
        }

        private void moveToHead(DListNode node) {
            remove(node);
            addToHead(node);
        }

        private DListNode removeTail() {
            DListNode tail = this.tail.prev;
            remove(tail);
            return tail;
        }

        private void remove(DListNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToHead(DListNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private class DListNode {
            private int key, value;
            private DListNode prev, next;

            public DListNode(){}
            public DListNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
