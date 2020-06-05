package com.bottomlord.week_048;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2020/6/5 8:39
 */
public class LeetCode_19_4 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode a = dummy, b = dummy;

        for (int i = 0; i < n + 1; i++) {
            a = a.next;
        }

        while (a != null) {
            a = a.next;
            b = b.next;
        }

        b.next = b.next.next;

        return dummy.next;
    }
}