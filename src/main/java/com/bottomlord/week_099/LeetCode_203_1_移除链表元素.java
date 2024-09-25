package com.bottomlord.week_099;

import com.bottomlord.ListNode;

public class LeetCode_203_1_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode fake = new ListNode(0);
        fake.next = head;
        ListNode pre = fake, node = head;
        while (node != null) {
            ListNode next = node.next;
            if (node.val == val) {
                pre.next = next;
            } else {
                pre = node;
            }
            node = next;
        }

        return fake.next;
    }
}
