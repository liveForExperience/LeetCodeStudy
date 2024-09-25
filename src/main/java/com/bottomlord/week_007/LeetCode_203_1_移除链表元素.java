package com.bottomlord.week_007;

import com.bottomlord.ListNode;

public class LeetCode_203_1_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode node = head;
        ListNode pre = null;
        while (node != null) {
            if (node.val == val) {
                if (pre == null) {
                    node = node.next;
                    head = node;
                    continue;
                }

                pre.next = node.next;
            } else {
                pre = node;
            }

            node = node.next;
        }

        return head;
    }
}
