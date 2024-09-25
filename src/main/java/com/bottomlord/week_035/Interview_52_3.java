package com.bottomlord.week_035;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/3/4 8:35
 */
public class Interview_52_3 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }
}
