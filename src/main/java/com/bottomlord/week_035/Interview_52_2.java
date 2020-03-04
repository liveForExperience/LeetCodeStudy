package com.bottomlord.week_035;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/3/4 8:32
 */
public class Interview_52_2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headA : a.next;
            b = b == null ? headB : b.next;
        }

        return a;
    }
}