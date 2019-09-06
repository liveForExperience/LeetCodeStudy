package com.bottomlord.week_009;

import com.bottomlord.ListNode;

public class LeetCode_707_1_设计链表 {
    class MyLinkedList {
        private ListNode head;
        private ListNode tail;
        private int len;

        public MyLinkedList() {
            ListNode node = new ListNode(0);
            this.head = node;
            this.tail = node;
            this.len = 0;
        }

        public int get(int index) {
            if (index < 0 || index >= len) {
                return -1;
            }

            ListNode node = this.head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.val;
        }

        public void addAtHead(int val) {
            if (this.len == 0) {
                this.head.val = val;
            } else {
                ListNode node = new ListNode(val);
                node.next = this.head;
                this.head = node;
            }
            this.len++;
        }

        public void addAtTail(int val) {
            if (this.len == 0) {
                this.tail.val = val;
            } else {
                ListNode node = new ListNode(val);
                this.tail.next = node;
                this.tail = node;
            }
            this.len++;
        }

        public void addAtIndex(int index, int val) {
            if (index > len) {
                return;
            }

            if (index == 0) {
                addAtHead(val);
            } else if (index == len) {
                addAtTail(val);
            } else {
                ListNode node = this.head;
                for (int i = 0; i < index - 1; i++) {
                    ListNode tmp = node.next;
                    node.next = new ListNode(val);
                    node.next.next = tmp;
                }
                this.len++;
            }
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= len) {
                return;
            }

            if (index == 0) {
                this.head = this.head.next;
                this.len--;
                return;
            }

            ListNode node = this.head;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            node.next = node.next.next;
            if (index == this.len - 1) {
                this.tail = node;
            }
            this.len--;
        }
    }
}
