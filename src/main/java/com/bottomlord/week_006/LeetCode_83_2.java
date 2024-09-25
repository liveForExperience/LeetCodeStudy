package com.bottomlord.week_006;

import com.bottomlord.ListNode;

public class LeetCode_83_2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }
}