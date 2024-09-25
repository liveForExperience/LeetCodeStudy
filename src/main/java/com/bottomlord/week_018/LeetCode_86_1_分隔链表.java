package com.bottomlord.week_018;

import com.bottomlord.ListNode;

public class LeetCode_86_1_分隔链表 {
    public ListNode partition(ListNode head, int x) {
        ListNode lessStart = new ListNode(0), less = lessStart, moreStart = new ListNode(0), more = moreStart;
        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                more.next = head;
                more = more.next;
            }

            head = head.next;
        }

        more.next = null;

        less.next = moreStart.next;

        return lessStart.next;
    }
}
