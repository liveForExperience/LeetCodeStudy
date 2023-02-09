package com.bottomlord.week_187;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-02-09 09:14:34
 */
public class LeetCode_1797_2 {
    class AuthenticationManager {

        private Map<String, DoubleLinkedListNode> map;
        private DoubleLinkedList list;
        private int timeToLive;

        public AuthenticationManager(int timeToLive) {
            this.map = new HashMap<>();
            this.list = new DoubleLinkedList();
            this.timeToLive = timeToLive;
        }

        public void generate(String tokenId, int currentTime) {
            DoubleLinkedListNode node = new DoubleLinkedListNode(tokenId, currentTime);
            map.put(tokenId, node);
            list.addToHead(node);
        }

        public void renew(String tokenId, int currentTime) {
            if (!map.containsKey(tokenId)) {
                return;
            }

            DoubleLinkedListNode node = map.get(tokenId);
            if (isExpired(node.currentTime, currentTime)) {
                map.remove(tokenId);
                list.remove(node);
                return;
            }

            node.currentTime = currentTime;
            list.remove(node);
            list.addToHead(node);
        }

        public int countUnexpiredTokens(int currentTime) {
            while (list.size() > 0 && list.getTail() != null && isExpired(list.getTail().currentTime, currentTime)) {
                DoubleLinkedListNode node = list.getTail();
                map.remove(node.tokenId);
                list.remove(node);
            }

            return list.size();
        }

        private boolean isExpired(int storeTime, int currentTime) {
            return storeTime + timeToLive <= currentTime;
        }

        private class DoubleLinkedList {

            private DoubleLinkedListNode head, tail;
            private int length;

            public DoubleLinkedList() {
                this.head = new DoubleLinkedListNode();
                this.tail = new DoubleLinkedListNode();
                this.head.next = tail;
                this.tail.pre = head;
                this.length = 0;
            }

            public void addToHead(DoubleLinkedListNode node) {
                node.next = this.head.next;
                node.pre = this.head;
                node.next.pre = node;
                node.pre.next = node;
                length++;
            }

            public DoubleLinkedListNode getTail() {
                return length == 0 ? null : this.tail.pre;
            }

            public int size() {
                return this.length;
            }

            public void remove(DoubleLinkedListNode node) {
                if (length == 0) {
                    return;
                }

                node.pre.next = node.next;
                node.next.pre = node.pre;
                length--;
            }
        }

        private class DoubleLinkedListNode {
            private DoubleLinkedListNode next, pre;
            private String tokenId;
            private Integer currentTime;

            public DoubleLinkedListNode() {}

            public DoubleLinkedListNode(String tokenId, Integer currentTime) {
                this.tokenId = tokenId;
                this.currentTime = currentTime;
            }
        }
    }
}
