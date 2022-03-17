package com.bottomlord.week_140;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-03-16 13:18:29
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
                    node.addKey(key);
                    valueMap.put(1, node);

                    if (head.next == tail) {
                        head.next = node;
                        node.pre = head;
                        node.next = tail;
                        tail.pre = node;
                        return;
                    }

                    node.pre = head;
                    node.next = head.next;
                    head.next = node;
                    node.next.pre = node;
                    return;
                }

                node = valueMap.get(1);
                node.addKey(key);
                return;
            }

            int oldValue = keyMap.get(key), curValue = oldValue + 1;
            keyMap.put(key, curValue);
            DoubleLinkedNode oldNode = valueMap.get(oldValue);
            oldNode.removeKey(key);

            DoubleLinkedNode curNode;
            if (!valueMap.containsKey(curValue)) {
                curNode = new DoubleLinkedNode(curValue);
                valueMap.put(curValue, curNode);
                oldNode.next.pre = curNode;
                curNode.pre = oldNode;
                curNode.next = oldNode.next;
                oldNode.next = curNode;
            }

            curNode = valueMap.get(curValue);
            curNode.addKey(key);

            if (oldNode.isEmpty()) {
                oldNode.pre.next = oldNode.next;
                oldNode.next.pre = oldNode.pre;
                valueMap.remove(oldValue);
            }
        }

        public void dec(String key) {
            if (!keyMap.containsKey(key)) {
                return;
            }

            int oldValue = keyMap.get(key);
            if (oldValue == 1) {
                keyMap.remove(key);
                DoubleLinkedNode oldNode = valueMap.get(1);
                oldNode.removeKey(key);

                if (oldNode.isEmpty()) {
                    oldNode.pre.next = oldNode.next;
                    oldNode.next.pre = oldNode.pre;
                    valueMap.remove(1);
                }

                return;
            }

            int curValue = oldValue - 1;
            keyMap.put(key, curValue);
            DoubleLinkedNode oldNode = valueMap.get(oldValue);
            oldNode.removeKey(key);

            DoubleLinkedNode curNode;
            if (!valueMap.containsKey(curValue)) {
                curNode = new DoubleLinkedNode(curValue);
                curNode.next = oldNode;
                curNode.pre = oldNode.pre;
                curNode.next.pre = curNode;
                curNode.pre.next = curNode;
            } else {
                curNode = valueMap.get(curValue);
            }

            curNode.addKey(key);

            if (oldNode.isEmpty()) {
                oldNode.pre.next = oldNode.next;
                oldNode.next.pre = oldNode.pre;
                valueMap.remove(oldValue);
            }

            valueMap.put(curValue, curNode);
        }

        public String getMaxKey() {
            return head.next == tail ? "" : tail.pre.set.stream().findFirst().get();
        }

        public String getMinKey() {
            return tail.pre == head ? "" : head.next.set.stream().findFirst().get();
        }

        private class DoubleLinkedNode {
            private final Integer val;
            private final Set<String> set;
            private DoubleLinkedNode pre, next;

            public DoubleLinkedNode(Integer val) {
                this.val = val;
                this.set = new HashSet<>();
            }

            public boolean isEmpty() {
                return this.set.isEmpty();
            }

            public void addKey(String key) {
                this.set.add(key);
            }

            public void removeKey(String key) {
                this.set.remove(key);
            }
        }
    }
}
