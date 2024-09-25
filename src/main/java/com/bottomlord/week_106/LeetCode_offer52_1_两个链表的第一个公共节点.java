package com.bottomlord.week_106;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2021/7/21 8:24
 */
public class LeetCode_offer52_1_两个链表的第一个公共节点 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode a = headA, b = headB;
        while (a != b) {
            a = a.next;
            b = b.next;

            if (a == null && b == null) {
                return null;
            }

            if (a == null) {
                a = headB;
            }

            if (b == null) {
                b = headA;
            }
        }

        return a;
    }
}
