package com.bottomlord.week_009;

import com.bottomlord.ListNode;

public class LeetCode_707_2 {
    class MyLinkedList {
        private ListNode preHead;
        private ListNode tail;
        private int len;

        public MyLinkedList() {
            ListNode node = new ListNode(0);
            this.preHead = node;
            this.tail = node;
            this.len = 0;
        }

        public int get(int index) {
            if (index < 0 || index >= len) {
                return -1;
            }

            return findNode(index).next.val;
        }

        public void addAtHead(int val) {
            if (this.len == 0) {
                addAtTail(val);
            } else {
                addAfter(this.preHead, val);
            }
        }

        public void addAtTail(int val) {
            addAfter(this.tail, val);
            this.tail = this.tail.next;
        }

        public void addAtIndex(int index, int val) {
            if (index < 0) {
                addAtHead(val);
            } else if (index == this.len) {
                addAtTail(val);
            } else if (index >= 0 && index < this.len) {
                addAfter(findNode(index), val);
            }
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= len) {
                return;
            }

            ListNode node = findNode(index);
            if (index == this.len - 1) {
                this.tail = node;
            }
            node.next = node.next.next;
            this.len--;
        }

        private ListNode findNode(int index) {
            ListNode node = this.preHead;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }

        private void addAfter(ListNode node, int val) {
            ListNode tmp = new ListNode(val);
            tmp.next = node.next;
            node.next = tmp;
            this.len++;
        }
    }
}
