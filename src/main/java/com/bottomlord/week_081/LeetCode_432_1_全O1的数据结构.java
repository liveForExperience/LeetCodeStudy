package com.bottomlord.week_081;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/1/25 15:41
 */
public class LeetCode_432_1_全O1的数据结构 {
    static class AllOne {
        private Map<String, Integer> keyMap;
        private Map<Integer, DoubleLinkedNode> valueMap;
        private DoubleLinkedNode head, tail;

        public AllOne() {
            this.keyMap = new HashMap<>();
            this.valueMap = new HashMap<>();
            this.head = new DoubleLinkedNode(0);
            this.tail = new DoubleLinkedNode(0);
            head.next = tail;
            tail.pre = head;
        }

        public void inc(String key) {
            if (!keyMap.containsKey(key)) {
                keyMap.put(key, 1);

                DoubleLinkedNode node;
                if (!valueMap.containsKey(1)) {
                    node = new DoubleLinkedNode(1);
                    valueMap.put(1, node);

                    if (head.next == tail) {
                        head.next = node;
                        node.pre = head;
                        tail.pre = node;
                        node.next = tail;
                        node.addKey(key);
                        return;
                    }

                    node.pre = head;
                    node.next = head.next;
                    head.next = node;
                    node.next.pre = node;
                } else {
                    node = valueMap.get(1);
                }

                node.addKey(key);

                return;
            }

            int curValue = keyMap.get(key), incValue = curValue + 1;

            keyMap.put(key, incValue);
            valueMap.get(curValue).removeKey(key);

            DoubleLinkedNode curNode = valueMap.get(curValue);

            DoubleLinkedNode incNode;
            if (!valueMap.containsKey(incValue)) {
                incNode = new DoubleLinkedNode(incValue);
                incNode.pre = curNode;
                incNode.next = curNode.next;
                incNode.next.pre = incNode;
                incNode.pre.next = incNode;
            } else {
                incNode = valueMap.get(incValue);
            }

            if (curNode.isEmpty()) {
                valueMap.remove(curValue);
                curNode.pre.next = curNode.next;
                curNode.next.pre = curNode.pre;
            }

            valueMap.put(incValue, incNode);
            incNode.addKey(key);
        }

        public void dec(String key) {
            if (!keyMap.containsKey(key)) {
                return;
            }

            if (keyMap.get(key) == 1) {
                keyMap.remove(key);
                valueMap.get(1).removeKey(key);

                DoubleLinkedNode node = valueMap.get(1);
                if (node.isEmpty()) {
                    valueMap.remove(node.val);
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                }

                return;
            }

            int curValue = keyMap.get(key), desValue = curValue - 1;
            keyMap.put(key, desValue);
            DoubleLinkedNode curNode = valueMap.get(curValue);
            curNode.removeKey(key);

            DoubleLinkedNode decNode;
            if (!valueMap.containsKey(desValue)) {
                decNode = new DoubleLinkedNode(desValue);
                decNode.pre = curNode.pre;
                decNode.next = curNode;
                decNode.pre.next = decNode;
                decNode.next.pre = decNode;
            } else {
                decNode = valueMap.get(desValue);
            }

            if (curNode.isEmpty()) {
                curNode.pre.next = curNode.next;
                curNode.next.pre = curNode.pre;
                valueMap.remove(curValue);
            }

            valueMap.put(desValue, decNode);
            decNode.addKey(key);
        }

        public String getMaxKey() {
            return tail.pre == head ? "" : tail.pre.set.stream().findFirst().get();
        }

        public String getMinKey() {
            return head.next == tail ? "" : head.next.set.stream().findFirst().get();
        }

        static class DoubleLinkedNode {
            private int val;
            private Set<String> set;
            private DoubleLinkedNode pre, next;

            public DoubleLinkedNode(int val) {
                this.val = val;
                this.set = new HashSet<>();
            }

            private void addKey(String key) {
                this.set.add(key);
            }

            private void removeKey(String key) {
                this.set.remove(key);
            }

            private boolean isEmpty() {
                return this.set.isEmpty();
            }
        }
    }
}