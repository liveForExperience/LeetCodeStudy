package com.bottomlord.week_039;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/4/5 13:42
 */
public class LeetCode_460_1_LFU缓存 {
    class LFUCache {
        private Map<Integer, Node> cache;
        private Map<Integer, LinkedHashSet<Node>> freqs;
        private int size;
        private int cap;
        private int min;

        public LFUCache(int capacity) {
            this.cache = new HashMap<>();
            this.freqs = new HashMap<>();
            this.size = 0;
            this.cap = capacity;
            this.min = 0;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            incrFreq(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (cap == 0) {
                return;
            }

            Node node = cache.get(key);
            if (node != null) {
                node.value = value;
                incrFreq(node);
            } else {
                if (size == cap) {
                    Node toDelNode = removeNode();
                    cache.remove(toDelNode.key);
                    size--;
                }
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                addNode(newNode);
                size++;
            }
        }

        private void addNode(Node newNode) {
            LinkedHashSet<Node> set = freqs.computeIfAbsent(1, k -> new LinkedHashSet<>());
            set.add(newNode);
            min = 1;
        }

        private Node removeNode() {
            LinkedHashSet<Node> set = freqs.get(min);
            Node toDelNode = set.iterator().next();
            set.remove(toDelNode);
            return toDelNode;
        }

        private void incrFreq(Node node) {
            int freq = node.freq;
            LinkedHashSet<Node> set = freqs.get(freq);
            set.remove(node);
            if (freq == min && set.size() == 0) {
                min = freq + 1;
            }

            node.freq++;
            LinkedHashSet<Node> newSet = freqs.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>());
            newSet.add(node);
        }
    }
}
