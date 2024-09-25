package com.bottomlord.week_019;

import com.bottomlord.ListNode;

public class LeetCode_143_1_重排链表 {
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head, P = null;

        while (fast != null && fast.next != null) {
            P = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (slow != null && P != null) {
            P.next = null;
            ListNode pre = null;

            while (slow != null) {
                ListNode next = slow.next;
                slow.next = pre;
                pre = slow;
                slow = next;
            }

            ListNode start = head;
            while (start != null) {
                ListNode firstNext = start.next;
                ListNode secondNext = pre.next;

                start.next = pre;
                if (firstNext != null) {
                    pre.next = firstNext;
                }

                start = firstNext;
                pre = secondNext;
            }
        }
    }
}