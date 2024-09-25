package com.bottomlord.week_087;

import java.util.LinkedList;

public class LeetCode_706_1_设计哈希映射 {
    class MyHashMap {
        private static final int BASE = 769;
        private LinkedList<Node>[] bucket;

        @SuppressWarnings("unchecked")
        public MyHashMap() {
            this.bucket = new LinkedList[BASE];
            for (int i = 0; i < BASE; i++) {
                bucket[i] = new LinkedList<>();
            }
        }

        public void put(int key, int value) {
            int index = hash(key);

            if (bucket[index].isEmpty()) {
                bucket[index].add(new Node(key, value));
            } else {
                LinkedList<Node> nodes = bucket[index];
                Node node = iteratorAction(nodes, key);
                if (node == null) {
                    nodes.add(new Node(key, value));
                } else {
                    node.value = value;
                }
            }
        }

        public int get(int key) {
            LinkedList<Node> nodes = bucket[hash(key)];
            if (nodes.isEmpty()) {
                return -1;
            } else {
                Node node = iteratorAction(nodes, key);
                return node == null ? -1 : node.value;
            }
        }

        public void remove(int key) {
            LinkedList<Node> nodes = bucket[hash(key)];
            if (nodes.isEmpty()) {
                return;
            }

            nodes.removeIf(node -> node.key == key);
        }

        private Node iteratorAction(LinkedList<Node> nodes, int key) {
            for (Node node : nodes) {
                if (node.key == key) {
                    return node;
                }
            }

            return null;
        }

        private int hash(int key) {
            return key % BASE;
        }

        private class Node {
            private int key;
            private int value;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
