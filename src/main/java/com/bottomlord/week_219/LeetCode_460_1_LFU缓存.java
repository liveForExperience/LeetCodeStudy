package com.bottomlord.week_219;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-09-25 22:15:49
 */
public class LeetCode_460_1_LFU缓存 {
    class LFUCache {

        private Map<Integer, DLinkedList> freqTable;
        private Map<Integer, Node> table;
        private int capacity, minFreq;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.freqTable = new HashMap<>();
            this.table = new HashMap<>();
            this.minFreq = 0;
        }

        public int get(int key) {
            if (!table.containsKey(key)) {
                return -1;
            }

            Node node = table.get(key);
            int freq = node.freq;
            freqTable.get(freq).remove(node);
            if (freqTable.get(freq).size == 0) {
                freqTable.remove(freq);
                if (freq == minFreq) {
                    minFreq++;
                }
            }

            DLinkedList list = freqTable.getOrDefault(freq + 1, new DLinkedList());
            node.freq++;
            list.addFirst(node);
            freqTable.put(freq + 1, list);
            return node.value;
        }

        public void put(int key, int value) {
            if (table.containsKey(key)) {
                Node node = table.get(key);
                node.value = value;
                int freq = node.freq;
                freqTable.get(freq).remove(node);

                if (freqTable.get(freq).size == 0) {
                    freqTable.remove(freq);

                    if (minFreq == freq) {
                        minFreq++;
                    }
                }

                DLinkedList list = freqTable.getOrDefault(freq + 1, new DLinkedList());
                node.freq++;
                list.addFirst(node);
                freqTable.put(freq + 1, list);
                table.put(key, node);
                return;
            }

            if (capacity == table.size()) {
                Node node = freqTable.get(minFreq).getLast();
                table.remove(node.key);
                freqTable.get(minFreq).remove(node);
                if (freqTable.get(minFreq).size == 0) {
                    freqTable.remove(minFreq);
                }
            }

            DLinkedList list = freqTable.getOrDefault(1, new DLinkedList());
            Node node = new Node(key, value, 1);
            list.addFirst(node);
            freqTable.put(1, list);
            table.put(key, node);
            minFreq = 1;
        }

        private class Node {
            private int key, value, freq;

            private Node prev, next;

            public Node() {
                this(-1, -1, 0);
            }

            public Node(int key, int value, int freq) {
                this.key = key;
                this.value = value;
                this.freq = freq;
            }
        }

        private class DLinkedList {
            private Node head, tail;

            private int size;

            public DLinkedList() {
                this.head = new Node();
                this.tail = new Node();
                head.next = tail;
                tail.prev = head;
                size = 0;
            }

            public void addFirst(Node node) {
                Node oldFirst = head.next;
                head.next = node;
                node.prev = head;
                node.next = oldFirst;
                oldFirst.prev = node;
                size++;
            }

            public void remove(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
            }

            public Node getFirst() {
                return head.next;
            }

            public Node getLast() {
                return tail.prev;
            }
        }
    }
}
