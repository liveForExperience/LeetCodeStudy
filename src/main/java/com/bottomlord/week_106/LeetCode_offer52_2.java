package com.bottomlord.week_106;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2021/7/21 8:32
 */
public class LeetCode_offer52_2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }
}
