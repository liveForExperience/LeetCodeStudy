package com.bottomlord.week_087;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/3/12 8:54
 */
public class LeetCode_146_1_LRU缓存机制 {
    static class LRUCache {
        private int capacity, size;
        private Map<Integer, Node> map;
        private Node head, tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.map = new HashMap<>();
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }

            Node node = remove(map.get(key));
            addHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                map.get(key).value = value;
                remove(map.get(key));
                addHead(map.get(key));
                return;
            }

            if (capacity == 0) {
                return;
            }

            Node node = new Node(key, value);
            if (size != capacity) {
                map.put(key, node);
                addHead(node);
                size++;
                return;
            }

            map.remove(tail.pre.key);
            remove(tail.pre);
            map.put(key, node);
            addHead(node);
        }

        private Node remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            return node;
        }

        private void addHead(Node node) {
            node.next = head.next;
            node.pre = head;
            node.pre.next = node;
            node.next.pre = node;
        }

        private void addTail(Node node) {
            node.next = tail;
            node.pre = tail.pre;
            node.pre.next = node;
            node.next.pre = node;
        }

        private class Node {
            private int key;
            private int value;
            private Node pre;
            private Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
