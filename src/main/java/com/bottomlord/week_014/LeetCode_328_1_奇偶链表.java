package com.bottomlord.week_014;

import com.bottomlord.ListNode;

public class LeetCode_328_1_奇偶链表 {
    public ListNode oddEvenList(ListNode head) {
        if (head.next == null) {
            return head;
        }

        ListNode odd = head, evenHead = head.next, even = head.next;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }
}
