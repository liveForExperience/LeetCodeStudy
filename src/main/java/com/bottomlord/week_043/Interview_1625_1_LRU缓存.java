package com.bottomlord.week_043;

import com.bottomlord.week_031.LeetCode_146_2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/4/27 8:46
 */
public class Interview_1625_1_LRU缓存 {
    class LRUCache {
        private Map<Integer, DoubleLinkedNode> map;
        private int size;
        private int capacity;
        private DoubleLinkedNode head;
        private DoubleLinkedNode tail;
        public LRUCache(int capacity) {
            map = new HashMap<>();
            head = new DoubleLinkedNode();
            tail = new DoubleLinkedNode();
            head.next = tail;
            tail.pre = head;
            this.capacity = capacity;
            this.size = 0;
        }

        public int get(int key) {
            if (size == 0 || !map.containsKey(key)) {
                return -1;
            }

            DoubleLinkedNode node = map.get(key);
            removeNode(node);
            addNode(node);

            return node.val;
        }

        public void put(int key, int value) {
            DoubleLinkedNode node = map.get(key);

            if (node != null) {
                removeNode(node);
                node.val = value;
                addNode(node);
            } else {
                node = new DoubleLinkedNode(key, value);
                addNode(node);
                map.put(node.key, node);
                size++;

                if (size > capacity) {
                    DoubleLinkedNode last = tail.pre;
                    removeNode(last);
                    map.remove(last.key);
                }
            }
        }

        private void removeNode(DoubleLinkedNode node) {
            node.next.pre = node.pre;
            node.pre.next = node.next;
        }

        private void addNode(DoubleLinkedNode node) {
            node.pre = head;
            node.next = head.next;

            head.next.pre = node;
            head.next = node;
        }
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
